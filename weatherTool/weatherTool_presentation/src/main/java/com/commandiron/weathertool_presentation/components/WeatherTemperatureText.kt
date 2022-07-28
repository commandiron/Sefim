package com.commandiron.weathertool_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.commandiron.core_ui.util.Strings

@Composable
fun WeatherTemperatureText(
    modifier: Modifier = Modifier,
    temperature: Double,
    centerText: Boolean = true
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(centerText){
            Text(
                text = Strings.Turkish.DEGREE_SYMBOL,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.background
            )
        }
        Text(
            text = temperature.toString(),
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = Strings.Turkish.DEGREE_SYMBOL,
            style = MaterialTheme.typography.displayLarge,
        )
    }
}