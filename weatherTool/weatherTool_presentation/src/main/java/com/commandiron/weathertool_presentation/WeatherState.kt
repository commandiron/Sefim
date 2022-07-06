package com.commandiron.weathertool_presentation

data class WeatherState(
    val isLoading: Boolean = false,
    val title: String = "",
    val myCity: String = "İstanbul",
    val locationPermissionGranted: Boolean = false,
    val weatherDescription: String = "--",
    val weatherTemp: String = "--",
    val weatherHumidity: String = "--",
    val weatherVisibility: String = "--",
    val weatherWindSpeed: String = "--",
)
