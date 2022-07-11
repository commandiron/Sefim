package com.commandiron.tools_presentation.components.stickers

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.commandiron.core_ui.util.LocalSpacing

@Composable
fun ToLeftSticker(
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .aspectRatio(1f),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiary,
        shadowElevation = spacing.spaceSmall
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowLeft,
            contentDescription = null,
            tint = Color.Green
        )
    }
}