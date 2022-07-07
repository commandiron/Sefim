package com.commandiron.weathertool_presentation

import com.commandiron.weathertool_domain.model.WeatherInfo

data class WeatherState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val title: String = "",
    val myCity: String = "İstanbul",
    val locationPermissionGranted: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
)
