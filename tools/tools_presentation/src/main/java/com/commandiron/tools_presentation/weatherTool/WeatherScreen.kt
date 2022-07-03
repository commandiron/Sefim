package com.commandiron.tools_presentation.weatherTool

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core.R
import com.commandiron.core_ui.LocalPermissionsState
import com.commandiron.core_ui.Strings.Turkish.DEGREE_SYMBOL
import com.commandiron.core_ui.Strings.Turkish.HUMIDITY
import com.commandiron.core_ui.Strings.Turkish.LOCATION_PERMISSION_REQUIRED
import com.commandiron.core_ui.Strings.Turkish.SIGHT
import com.commandiron.core_ui.Strings.Turkish.TODAY_REPORT
import com.commandiron.core_ui.Strings.Turkish.WIND
import com.commandiron.core_ui.components.OnLifecycleEvent
import com.commandiron.tools_presentation.weatherTool.components.CheckFineLocationPermission
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val spacing = LocalSpacing.current
    val permissionsState = LocalPermissionsState.current
    val state = viewModel.state
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
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPadding),
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
                        MaterialTheme.colorScheme.primary
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
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.25f),
            painter = painterResource(id = R.drawable.partyly_cloudy),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = state.weatherDescription,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = DEGREE_SYMBOL,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
                color = MaterialTheme.colorScheme.background
            )
            Text(
                text = state.weatherTemp,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
            )
            Text(
                text = DEGREE_SYMBOL,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.padding(
                    vertical = spacing.spaceLarge,
                    horizontal = spacing.spaceMedium
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${state.weatherHumidity}%",
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
                        text = "${state.weatherVisibility} km",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = SIGHT)
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
                        text = "${state.weatherWindSpeed} km",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = WIND)
                }
            }
        }
    }
}