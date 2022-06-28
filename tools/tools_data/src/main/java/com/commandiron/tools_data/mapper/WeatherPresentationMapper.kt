package com.commandiron.tools_data.mapper

import com.commandiron.tools_data.remote.dto.WeatherDto
import com.commandiron.tools_domain.model.WeatherPresentation
import kotlin.math.roundToInt

fun WeatherDto.toWeatherPresentation(): WeatherPresentation {
    return WeatherPresentation(
        description = weather[0].description,
        temp = (main.temp - 273.15).roundToInt().toString(),
        humidity = main.humidity.toString(),
        visibility = (visibility/1000) .toString(),
        windSpeed = wind.speed.toString()
    )
}