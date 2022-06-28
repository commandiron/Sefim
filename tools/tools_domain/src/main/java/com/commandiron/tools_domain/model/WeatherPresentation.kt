package com.commandiron.tools_domain.model

data class WeatherPresentation(
    val description: String,
    val temp: String,
    val humidity: String,
    val visibility: String,
    val windSpeed: String
)
