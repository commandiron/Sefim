package com.commandiron.weathertool_domain.use_cases

import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.maps.model.LatLng

class GetCityFromLatLng(
    private val repository: WeatherToolRepository
) {
    operator fun invoke(latLng: LatLng): String = repository.getCityAndCountryNameFromLatLng(latLng)
}