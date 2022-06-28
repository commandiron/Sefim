package com.commandiron.tools_presentation.weather

sealed class WeatherUserEvent{
    object BackTextClick: WeatherUserEvent()
    object FineLocationPermissionGranted: WeatherUserEvent()
    object FineLocationPermissionDenied: WeatherUserEvent()
}
