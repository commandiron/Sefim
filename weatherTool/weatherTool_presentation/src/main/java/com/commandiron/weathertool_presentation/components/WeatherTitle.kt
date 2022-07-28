package com.commandiron.weathertool_presentation.components

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.util.Strings
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherTitle() {
    Text(
        text = "${Strings.Turkish.TODAY_REPORT} ",
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
    )
    Text(
        text = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
        style = MaterialTheme.typography.titleSmall,
        color = LocalContentColor.current.copy(alpha = 0.5f)
    )
}