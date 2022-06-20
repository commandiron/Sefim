package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.commandiron.sefim.core.LocalSpacing

@Composable
fun UnFavoriteSticker(
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .aspectRatio(1f),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            modifier = Modifier.padding(spacing.spaceExtraSmall),
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}