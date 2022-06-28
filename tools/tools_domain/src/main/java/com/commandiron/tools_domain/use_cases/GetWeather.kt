package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.repository.ToolsRepository
import com.google.android.gms.maps.model.LatLng

class GetWeather(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(latLng: LatLng) =
        repository.getWeather(
            latitude = latLng.latitude.toString(),
            longitude = latLng.longitude.toString()
        )
}