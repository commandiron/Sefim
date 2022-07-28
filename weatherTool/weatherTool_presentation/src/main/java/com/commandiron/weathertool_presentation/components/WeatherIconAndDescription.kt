package com.commandiron.weathertool_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeatherIconAndDescription(
    modifier: Modifier = Modifier,
    iconRes: Int,
    weatherDesc: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.weight(4f),
            painter = painterResource(
                id = iconRes
            ),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = weatherDesc,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}