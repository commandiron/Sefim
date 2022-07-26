package com.commandiron.weathertool_data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
