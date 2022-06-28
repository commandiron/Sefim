package com.commandiron.tools_presentation.components.stickers

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.commandiron.core_ui.LocalSpacing

@Composable
fun LockedSticker(
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .aspectRatio(1f),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f),
        shadowElevation = spacing.spaceSmall
    ) {
        Icon(
            modifier = Modifier.padding(spacing.spaceExtraSmall),
            imageVector = Icons.Default.Lock,
            contentDescription = null
        )
    }
}