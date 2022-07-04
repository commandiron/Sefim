package com.commandiron.tools_presentation.weatherTool

import com.google.android.gms.maps.model.LatLng

data class WeatherState(
    val isLoading: Boolean = false,
    val title: String = "",
    val myLatLng: LatLng = LatLng(41.015137,28.979530),
    val myCity: String = "İstanbul",
    val locationPermissionGranted: Boolean = false,
    val weatherDescription: String = "--",
    val weatherTemp: String = "--",
    val weatherHumidity: String = "--",
    val weatherVisibility: String = "--",
    val weatherWindSpeed: String = "--",
)
