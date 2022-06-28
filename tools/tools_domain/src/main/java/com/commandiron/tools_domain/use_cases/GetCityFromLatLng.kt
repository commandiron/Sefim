package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.repository.ToolsRepository
import com.google.android.gms.maps.model.LatLng

class GetCityFromLatLng(
    private val repository: ToolsRepository
) {
    operator fun invoke(latLng: LatLng): String = repository.getCityAndCountryNameFromLatLng(latLng)
}