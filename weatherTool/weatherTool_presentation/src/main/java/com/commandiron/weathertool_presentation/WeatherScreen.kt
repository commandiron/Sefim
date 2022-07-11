package com.commandiron.weathertool_presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.commandiron.core_ui.util.Strings.Turkish.DEGREE_SYMBOL
import com.commandiron.core_ui.util.Strings.Turkish.HUMIDITY
import com.commandiron.core_ui.util.Strings.Turkish.LOCATION_PERMISSION_REQUIRED
import com.commandiron.core_ui.util.Strings.Turkish.TODAY_REPORT
import com.commandiron.core_ui.util.Strings.Turkish.WIND
import com.commandiron.core_ui.components.OnLifecycleEvent
import com.commandiron.core_ui.theme.MyPrimaryColor
import com.commandiron.core_ui.util.*
import com.commandiron.core_ui.util.Strings.Turkish.FAILED_TO_GET_LOCATION_PLEASE_OPEN_GOOGLE_MAPS
import com.commandiron.core_ui.util.Strings.Turkish.PRESSURE
import com.commandiron.weathertool_presentation.components.CheckFineLocationPermission
import com.commandiron.weathertool_presentation.components.HourlyWeatherDisplay
import java.text.SimpleDateFormat
import java.util.*

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .clickable { viewModel.onEvent(WeatherUserEvent.Back) },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.alignBy(LastBaseline),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = if(state.locationPermissionGranted){
                        MyPrimaryColor
                    } else{ MaterialTheme.colorScheme.error }
                )
                if(state.locationPermissionGranted){
                    Text(
                        text = state.myCity,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }else{
                    Text(
                        text = LOCATION_PERMISSION_REQUIRED,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "$TODAY_REPORT ",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
            style = MaterialTheme.typography.titleSmall,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
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
                    Text(text = FAILED_TO_GET_LOCATION_PLEASE_OPEN_GOOGLE_MAPS )
                }
            }
            state.weatherInfo?.let { weatherInfo ->
                weatherInfo.currentWeatherData?.let { currentWeatherData ->
                    Icon(
                        modifier = Modifier.weight(2f),
                        painter = painterResource(id = currentWeatherData.weatherType.iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = currentWeatherData.weatherType.weatherDesc,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    )
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Row() {
                            Text(
                                text = DEGREE_SYMBOL,
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.background
                            )
                            Text(
                                text = currentWeatherData.temperatureCelsius.toString(),
                                style = MaterialTheme.typography.displayLarge
                            )
                            Text(
                                text = DEGREE_SYMBOL,
                                style = MaterialTheme.typography.displayLarge,
                            )
                        }
                    }
                    state.weatherInfo.weatherDataPerDay[0]?.let { data ->
                        LazyRow(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(data) { weatherData ->
                                HourlyWeatherDisplay(
                                    weatherData = weatherData,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.humidity}%",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = HUMIDITY)
                            }
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight(0.5f),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.pressure} hpa",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = PRESSURE)
                            }
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight(0.5f),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.windSpeed} km/h",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = WIND)
                            }
                        }
                    }
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .clickable { viewModel.onEvent(WeatherUserEvent.Back) },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.alignBy(LastBaseline),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = if(state.locationPermissionGranted){
                        MyPrimaryColor
                    } else{ MaterialTheme.colorScheme.error }
                )
                if(state.locationPermissionGranted){
                    Text(
                        text = state.myCity,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }else{
                    Text(
                        text = LOCATION_PERMISSION_REQUIRED,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "$TODAY_REPORT ",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
            style = MaterialTheme.typography.titleSmall,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }else{
            Column(modifier = Modifier.fillMaxWidth()) {
                state.weatherInfo?.currentWeatherData?.let { currentWeatherData ->
                    Row(modifier = Modifier.weight(3f)) {
                        Row(modifier = Modifier.weight(1f)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = currentWeatherData.weatherType.iconRes
                                    ),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                                Text(
                                    text = currentWeatherData.weatherType.weatherDesc,
                                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = DEGREE_SYMBOL,
                                    style = MaterialTheme.typography.displayLarge,
                                    color = MaterialTheme.colorScheme.background
                                )
                                Text(
                                    text = currentWeatherData.temperatureCelsius.toString(),
                                    style = MaterialTheme.typography.displayLarge
                                )
                                Text(
                                    text = DEGREE_SYMBOL,
                                    style = MaterialTheme.typography.displayLarge,
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight().weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            state.weatherInfo.weatherDataPerDay[0]?.let { data ->
                                LazyRow(modifier = Modifier.fillMaxHeight(0.5f)) {
                                    items(data) { weatherData ->
                                        HourlyWeatherDisplay(
                                            weatherData = weatherData,
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(horizontal = 16.dp)
                                        )
                                    }
                                }
                            }
                        }

                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.humidity}%",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = HUMIDITY)
                            }
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight(0.5f),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.pressure} hpa",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = PRESSURE)
                            }
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight(0.5f),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeatherData.windSpeed} km/h",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = WIND)
                            }
                        }
                    }
                }
            }
        }
    }
}