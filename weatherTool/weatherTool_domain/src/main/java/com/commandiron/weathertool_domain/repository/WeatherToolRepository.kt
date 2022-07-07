package com.commandiron.weathertool_domain.repository

import android.location.Location
import com.commandiron.core.util.Response
import com.google.android.gms.maps.model.LatLng
import com.commandiron.weathertool_domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherToolRepository {
    suspend fun getUserLastKnownPosition(): Response<Location>
    fun getCityAndCountryNameFromLatLng(latLng: LatLng): String
    suspend fun getWeatherData(lat: Double, long: Double) : Flow<Response<WeatherInfo>>
}