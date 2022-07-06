package com.commandiron.weathertool_data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.commandiron.core.util.Response
import com.commandiron.core.util.Strings
import com.commandiron.weathertool_data.mapper.toWeatherPresentation
import com.commandiron.weathertool_data.remote.OpenWeatherApi
import com.commandiron.weathertool_domain.model.WeatherPresentation
import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.coroutines.resume

class WeatherToolRepositoryImpl(
    private val api: OpenWeatherApi,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val application: Application
): WeatherToolRepository {
    override suspend fun getUserLastKnownPosition(): Response<Location> {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled){
            return Response.Error(Strings.ExceptionMessages.LOCATION_IS_NULL)
        }

        return suspendCancellableCoroutine { cont ->
            fusedLocationClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(Response.Success(result))
                    }else{
                        cont.resume(Response.Error(Strings.ExceptionMessages.LOCATION_FAILED))
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(Response.Success(it))
                }
                addOnFailureListener {
                    cont.resume(Response.Error(Strings.ExceptionMessages.LOCATION_FAILED))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    override fun getCityAndCountryNameFromLatLng(latLng: LatLng): String {
        val geocoder = Geocoder(application, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        return addresses[0].adminArea + ", " +addresses[0].countryName
    }

    override suspend fun getWeather(
        latitude: String,
        longitude: String,
    ): Flow<Response<WeatherPresentation>> = flow {
        emit(Response.Loading)
        try {
            val weatherPresentation = api.getWeather(latitude, longitude).toWeatherPresentation()
            emit(Response.Success(weatherPresentation))
        }catch (e: Exception){
            emit(Response.Error(e.message ?: Strings.ExceptionMessages.AN_ERROR_OCCURRED))
        }
    }
}