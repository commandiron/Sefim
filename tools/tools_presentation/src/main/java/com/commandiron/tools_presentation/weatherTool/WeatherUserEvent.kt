package com.commandiron.tools_presentation.weatherTool

sealed class WeatherUserEvent{
    object BackTextClick: WeatherUserEvent()
    object FineLocationPermissionGranted: WeatherUserEvent()
    object FineLocationPermissionDenied: WeatherUserEvent()
}
