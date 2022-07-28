package com.commandiron.weathertool_presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.util.Strings

@Composable
fun WeatherInfoCard(
    modifier: Modifier = Modifier,
    humidity: Double,
    pressure: Double,
    windSpeed: Double
) {
    Card(
        modifier = modifier,
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
                    text = "${humidity}%",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = Strings.Turkish.HUMIDITY)
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
                    text = "${pressure} hpa",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = Strings.Turkish.PRESSURE)
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
                    text = "${windSpeed} km/h",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = Strings.Turkish.WIND)
            }
        }
    }
}