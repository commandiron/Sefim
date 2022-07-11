package com.commandiron.weathertool_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetCityFromLatLng(
    private val repository: WeatherToolRepository
) {
    suspend operator fun invoke(latLng: LatLng): Flow<Response<String>> = repository.getCityAndCountryNameFromLatLng(latLng)
}