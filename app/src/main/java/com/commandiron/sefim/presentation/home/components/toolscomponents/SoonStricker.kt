package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.sefim.core.LocalSpacing

@Composable
fun SoonSticker(
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.tertiary,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = spacing.spaceExtraSmall
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = spacing.spaceSmall),
                text = "Yakında",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}