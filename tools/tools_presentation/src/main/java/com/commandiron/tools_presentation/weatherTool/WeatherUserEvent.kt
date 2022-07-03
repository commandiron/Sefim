package com.commandiron.tools_presentation.weatherTool

sealed class WeatherUserEvent{
    object Back: WeatherUserEvent()
    object FineLocationPermissionGranted: WeatherUserEvent()
    object FineLocationPermissionDenied: WeatherUserEvent()
}
