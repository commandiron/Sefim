package com.commandiron.weathertool_domain.model

data class WeatherInfo(
    val weatherDataTodayPerHour: List<WeatherData>,
    val currentWeatherData: WeatherData?
)
