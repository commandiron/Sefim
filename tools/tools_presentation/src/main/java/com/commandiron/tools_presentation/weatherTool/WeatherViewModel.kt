package com.commandiron.tools_presentation.weatherTool

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core.util.UiEvent
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val toolsUseCases: ToolsUseCases,
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
            WeatherUserEvent.BackTextClick -> {
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
            toolsUseCases.getUserLastKnownPosition().onEach { response ->
                when (response) {
                    is Response.Error -> {

                    }
                    Response.Loading -> {

                    }
                    is Response.Success -> {
                        getLatLngFromLocation(response.data)
                        getCityFromLatLng()
                        getWeatherFromLatLng()                    }
                }
            }.collect()
        }
    }

    private fun getLatLngFromLocation(location: Location){
        val latLng = toolsUseCases.getLatLngFromLocation(location)
        state = state.copy(
            myLatLng = latLng
        )
    }
    private fun getCityFromLatLng(){
        val myLocationCity = toolsUseCases.getCityFromLatLng(state.myLatLng)
        state = state.copy(
            myCity = myLocationCity
        )
    }

    private fun getWeatherFromLatLng(){
        viewModelScope.launch {
            toolsUseCases.getWeather(state.myLatLng).onEach { response ->
                when(response){
                    is Response.Error -> {}
                    Response.Loading -> {}
                    is Response.Success -> {
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