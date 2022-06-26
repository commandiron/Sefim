package com.commandiron.tools_presentation.components.stickers

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.R

@Composable
fun CamSticker(
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
            painter = painterResource(id = R.drawable.ic_augmented_reality),
            contentDescription = null,
            modifier = Modifier.padding(spacing.spaceExtraSmall)
        )
    }
}