package com.commandiron.core_ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.R

@Composable
fun AppLogoWithName(
    modifier: Modifier = Modifier,
    alpha: Float = 1.0f,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo(
            alpha = alpha,
            color = color
        )
        AppName(
            alpha = alpha,
            color = color
        )
    }
}

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    alpha: Float = 1.0f,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.app_icon),
        contentDescription = null,
        tint = color.copy(
            alpha = alpha
        )
    )
}

@Composable
fun AppName(
    modifier: Modifier = Modifier,
    alpha: Float = 1.0f,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Text(
        modifier = modifier,
        text = "Şefim",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = color.copy(
            alpha = alpha
        )
    )
}