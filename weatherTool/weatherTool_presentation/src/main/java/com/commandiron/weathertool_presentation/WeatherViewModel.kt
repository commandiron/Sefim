package com.commandiron.weathertool_presentation

import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.weathertool_domain.use_cases.WeatherToolUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherToolUseCases: WeatherToolUseCases,
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getUserLastKnownPosition()
    }

    fun onEvent(userEvent: WeatherUserEvent) {
        when (userEvent) {
            WeatherUserEvent.Back -> {
                sendUiEvent(UiEvent.NavigateUp)
            }
            WeatherUserEvent.FineLocationPermissionDenied -> {
                state = state.copy(
                    locationPermissionGranted = false
                )
            }
            WeatherUserEvent.FineLocationPermissionGranted -> {
                state = state.copy(
                    locationPermissionGranted = true
                )
                getUserLastKnownPosition()
            }
        }
    }

    private fun getUserLastKnownPosition(){
        viewModelScope.launch {
            weatherToolUseCases.getUserLastKnownPosition().also { response ->
                when (response) {
                    is Response.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                    }
                    Response.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Response.Success -> {
                        state = state.copy(
                            isLoading = false
                        )
                        getCityFromLocation(response.data)
                        getWeatherFromLocation(response.data)
                    }
                }
            }
        }
    }

    private fun getCityFromLocation(location: Location){
        val myLocationCity = weatherToolUseCases
            .getCityFromLatLng(weatherToolUseCases.getLatLngFromLocation(location))
        state = state.copy(
            myCity = myLocationCity
        )
    }

    private fun getWeatherFromLocation(location: Location){
        viewModelScope.launch {
            weatherToolUseCases.getWeather(
                weatherToolUseCases.getLatLngFromLocation(location)
            ).onEach { response ->
                when(response){
                    is Response.Error -> {
                        Log.e("WeatherViewModel", response.message)
                        state = state.copy(
                            isLoading = false
                        )
                    }
                    Response.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Response.Success -> {
                        state = state.copy(
                            isLoading = false
                        )
                        state = state.copy(
                            weatherDescription = response.data.description,
                            weatherTemp = response.data.temp,
                            weatherHumidity = response.data.humidity,
                            weatherVisibility = response.data.visibility,
                            weatherWindSpeed = response.data.windSpeed
                        )
                    }
                }
            }.collect()
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}