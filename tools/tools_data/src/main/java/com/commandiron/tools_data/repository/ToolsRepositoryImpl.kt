package com.commandiron.tools_data.repository

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import com.commandiron.core.util.Response
import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toTool
import com.commandiron.tools_data.mapper.toWeatherPresentation
import com.commandiron.tools_data.remote.OpenWeatherApi
import com.commandiron.tools_data.remote.dto.WeatherDto
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.WeatherPresentation
import com.commandiron.tools_domain.repository.ToolsRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
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
            emit(Response.Error(e.message ?: "SOMETHING HAPPENED"))
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
                    trySend(Response.Success(task.result))
                }else{
                    trySend(Response.Error(task.exception?.message?: "Location Failed"))
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
            emit(Response.Error(e.message ?: "Something bad happened"))
        }
    }
}