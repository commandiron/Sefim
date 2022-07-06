package com.commandiron.weathertool_presentation

sealed class WeatherUserEvent{
    object Back: WeatherUserEvent()
    object FineLocationPermissionGranted: WeatherUserEvent()
    object FineLocationPermissionDenied: WeatherUserEvent()
}
