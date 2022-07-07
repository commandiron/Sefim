package com.commandiron.weathertool_domain.use_cases

import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.maps.model.LatLng

class GetWeather(
    private val repository: WeatherToolRepository
) {
    suspend operator fun invoke(latLng: LatLng) =
        repository.getWeatherData(
            lat = latLng.latitude,
            long = latLng.longitude
        )
}