package com.commandiron.tools_data.repository

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import com.commandiron.core.util.Response
import com.commandiron.core.util.Strings.ExceptionMessages.LOCATION_FAILED
import com.commandiron.core.util.Strings.ExceptionMessages.LOCATION_IS_NULL
import com.commandiron.core.util.Strings.ExceptionMessages.SOMETHING_BAD_HAPPENED
import com.commandiron.tools_data.BuildConfig
import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toTool
import com.commandiron.tools_data.mapper.toWeatherPresentation
import com.commandiron.tools_data.remote.OpenWeatherApi
import com.commandiron.tools_domain.model.RebarPrice
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.WeatherPresentation
import com.commandiron.tools_domain.repository.ToolsRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.text.SimpleDateFormat
import java.util.*

class ToolsRepositoryImpl(
    private val dao: ToolsDao,
    private val api: OpenWeatherApi,
    private val fusedLocationClient: FusedLocationProviderClient
): ToolsRepository {
    override suspend fun insertAllTools(tools: List<Tool>) {
        dao.insertAllTools(tools.map { it.toToolEntity() })
    }

    override suspend fun getAllTools(): List<Tool> {
        return dao
            .getAllTools()
            .map { entities ->
                entities.toTool()
            }
    }

    override suspend fun insertTool(tool: Tool) : Flow<Response<Unit>> = flow {
        emit(Response.Loading)
        try {
            dao.insertTool(tool.toToolEntity())
            emit(Response.Success(Unit))
        }catch (e: Exception){
            emit(Response.Error(e.message ?: SOMETHING_BAD_HAPPENED))
        }
    }

    override suspend fun getUserLastKnownPosition(): Flow<Response<Location>> = callbackFlow {
        send(Response.Loading)
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            fusedLocationClient.applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val coarseLocationPermission = ActivityCompat.checkSelfPermission(
            fusedLocationClient.applicationContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if(fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            coarseLocationPermission == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result?.let {
                        trySend(Response.Success(task.result))
                    } ?: run {
                        trySend(Response.Error(task.exception?.message?: LOCATION_IS_NULL))
                    }
                }else{
                    trySend(Response.Error(task.exception?.message?: LOCATION_FAILED))
                }
            }
        }else{
            trySend(Response.Error("Location Permission Not Granted"))
        }
        awaitClose {
            channel.close()
        }
    }

    override fun getCityAndCountryNameFromLatLng(latLng: LatLng): String {
        val geocoder = Geocoder(fusedLocationClient.applicationContext, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        return addresses[0].adminArea + ", " +addresses[0].countryName
    }

    override suspend fun getWeather(latitude: String, longitude: String) : Flow<Response<WeatherPresentation>> = flow {
        emit(Response.Loading)
        try {
            val weatherPresentation = api.getWeather(latitude, longitude).toWeatherPresentation()
            emit(Response.Success(weatherPresentation))
        }catch (e: Exception){
            emit(Response.Error(e.message ?: SOMETHING_BAD_HAPPENED))
        }
    }

    override suspend fun getRebarPrices(): Flow<Response<List<RebarPrice>>> = callbackFlow {
        try {
            withContext(Dispatchers.IO) {
                runCatching {
                    send(Response.Loading)
                    val rebarPrices = mutableListOf<RebarPrice>()
                    val dataForLoop: Elements?

                    val DATA_URL = BuildConfig.JSOUP_DATA_URL

                    val doc = Jsoup.connect(DATA_URL).get()
                    val cssQuery = "body > div.body > div > div:nth-child(1) > div > div.col-md-8.mb-5.mb-lg-0.order-first.order-md-2 > div.card.analiztablocard > div.card-body > div > table > tbody"
                    val fullData = doc.select(cssQuery)
                    dataForLoop = fullData.first()?.children()?.select("tr")

                    dataForLoop?.let { elements ->
                        for(i in elements){
                            val dataChildren = i.children()
                            val rebarPrice =
                                RebarPrice(
                                    date = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
                                    city = dataChildren.select("th").text(),
                                    q8mmPrice = dataChildren.select("td:nth-child(2)").text(),
                                    q10mmPrice = dataChildren.select("td:nth-child(3)").text(),
                                    q1232mmPrice = dataChildren.select("td:nth-child(4)").text()
                                )
                            rebarPrices += rebarPrice
                        }
                    }
                    send(Response.Success(rebarPrices))
                }
            }

        }catch (e: Exception){
            send(Response.Error(e.message ?: SOMETHING_BAD_HAPPENED))
        }
        awaitClose {
            channel.close()
        }
    }
}