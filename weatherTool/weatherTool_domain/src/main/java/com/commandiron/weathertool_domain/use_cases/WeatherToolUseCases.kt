package com.commandiron.weathertool_domain.use_cases

class WeatherToolUseCases(
    val getUserLastKnownPosition: GetUserLastKnownPosition,
    val getLatLngFromLocation: GetLatLngFromLocation,
    val getCityFromLatLng: GetCityFromLatLng,
    val getWeather: GetWeather,
)