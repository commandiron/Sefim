package com.commandiron.weathertool_presentation

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.weathertool_domain.use_cases.WeatherToolUseCases
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
            WeatherUserEvent.Refresh -> {
                getUserLastKnownPosition()
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
                            isLoading = false,
                            hasError = true
                        )
                    }
                    Response.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            hasError = false
                        )
                    }
                    is Response.Success -> {
                        state = state.copy(
                            isLoading = false,
                            hasError = false
                        )
                        getCityFromLocation(response.data)
                        getWeather(weatherToolUseCases.getLatLngFromLocation(response.data))
                    }
                }
            }
        }
    }

    private fun getCityFromLocation(location: Location){
        viewModelScope.launch {
            weatherToolUseCases
                .getCityFromLatLng(weatherToolUseCases.getLatLngFromLocation(location))
                .collect{ response ->
                    when(response){
                        is Response.Error -> {
                        }
                        Response.Loading -> {
                        }
                        is Response.Success -> {
                            state = state.copy(
                                myCity = response.data
                            )
                        }
                    }
                }
        }
    }

    private fun getWeather(latLng: LatLng){
        viewModelScope.launch {
            weatherToolUseCases.getWeather(latLng).collect { response ->
                when(response){
                    is Response.Error -> {
                        state = state.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                    Response.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            hasError = false
                        )
                    }
                    is Response.Success -> {
                        state = state.copy(
                            isLoading = false,
                            hasError = false
                        )
                        state = state.copy(
                            weatherInfo = response.data
                        )
                    }
                }
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}