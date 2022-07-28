package com.commandiron.weathertool_presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.commandiron.core_ui.components.OnLifecycleEvent
import com.commandiron.core_ui.util.*
import com.commandiron.core_ui.util.Strings.Turkish.FAILED_TO_GET_LOCATION
import com.commandiron.weathertool_presentation.components.*

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navigateUp:() -> Unit,
    showSnackbar:(String) -> Unit
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    val permissionsState = LocalPermissionsState.current
    CheckFineLocationPermission(
        permissionsState = permissionsState,
        permissionGranted = {
            viewModel.onEvent(WeatherUserEvent.FineLocationPermissionGranted)
        },
        permissionDenied = {
            viewModel.onEvent(WeatherUserEvent.FineLocationPermissionDenied)
        }
    )
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                permissionsState.launchMultiplePermissionRequest()
            }
            else -> return@OnLifecycleEvent
        }
    }
    LaunchedEffect(key1 = true){
        permissionsState.launchMultiplePermissionRequest()
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.NavigateUp -> { navigateUp() }
                is UiEvent.ShowSnackbar -> {showSnackbar(event.message)}
                else -> {}
            }
        }
    }
    if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        WeatherCompactContent(viewModel)
    }else{
        WeatherExpandedContent(viewModel)
    }

}
@Composable
fun WeatherCompactContent(viewModel: WeatherViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact),
        verticalArrangement = Arrangement.Center
    ) {
        WeatherHeader(
            state = state,
            onBackClick = { viewModel.onEvent(WeatherUserEvent.Back) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        WeatherTitle()
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(state.isLoading){
                CircularProgressIndicator()
            }
            if(state.hasError){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = {viewModel.onEvent(WeatherUserEvent.Refresh)}) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                    }
                    Text(text = FAILED_TO_GET_LOCATION )
                }
            }
            state.weatherInfo?.let { weatherInfo ->
                weatherInfo.currentWeatherData?.let { currentWeatherData ->
                    WeatherIconAndDescription(
                        modifier = Modifier
                            .weight(3f),
                        iconRes = currentWeatherData.weatherType.iconRes,
                        weatherDesc = currentWeatherData.weatherType.weatherDesc
                    )
                    WeatherTemperatureText(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterHorizontally),
                        temperature = currentWeatherData.temperatureCelsius
                    )
                    LazyRow(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(state.weatherInfo.weatherDataTodayPerHour) { weatherDataPerHour ->
                            HourlyWeatherDisplay(
                                weatherData = weatherDataPerHour,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    WeatherInfoCard(
                        modifier = Modifier.weight(1f),
                        humidity = currentWeatherData.humidity,
                        pressure = currentWeatherData.pressure,
                        windSpeed = currentWeatherData.windSpeed
                    )
                }
            }
        }
    }
}
@Composable
fun WeatherExpandedContent(viewModel: WeatherViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForExpandedNoBottomNav)
    ) {
        WeatherHeader(
            state = state,
            onBackClick = { viewModel.onEvent(WeatherUserEvent.Back) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        WeatherTitle()
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }else{
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                state.weatherInfo?.currentWeatherData?.let { currentWeatherData ->
                    Row(
                        modifier = Modifier.weight(3f)
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            WeatherIconAndDescription(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(spacing.spaceSmall),
                                iconRes = currentWeatherData.weatherType.iconRes,
                                weatherDesc = currentWeatherData.weatherType.weatherDesc
                            )
                            WeatherTemperatureText(
                                modifier = Modifier.weight(1f),
                                temperature = currentWeatherData.temperatureCelsius,
                                centerText = false
                            )
                        }
                        LazyVerticalGrid(
                            modifier = Modifier
                                .weight(1f),
                            columns = GridCells.Fixed(4),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                        ){
                            items(state.weatherInfo.weatherDataTodayPerHour){ weatherDataPerHour ->
                                HourlyWeatherDisplay(
                                    weatherData = weatherDataPerHour
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(spacing.spaceXXLarge))
                            }
                        }
                    }
                    WeatherInfoCard(
                        modifier = Modifier.weight(1f),
                        humidity = currentWeatherData.humidity,
                        pressure = currentWeatherData.pressure,
                        windSpeed = currentWeatherData.windSpeed
                    )
                }
            }
        }
    }
}