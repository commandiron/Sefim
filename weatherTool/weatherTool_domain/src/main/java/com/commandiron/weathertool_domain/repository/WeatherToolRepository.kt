package com.commandiron.weathertool_domain.repository

import android.location.Location
import com.commandiron.core.util.Response
import com.commandiron.weathertool_domain.model.WeatherPresentation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface WeatherToolRepository {
    suspend fun getUserLastKnownPosition(): Response<Location>
    fun getCityAndCountryNameFromLatLng(latLng: LatLng): String
    suspend fun getWeather(latitude: String, longitude: String) : Flow<Response<WeatherPresentation>>
}