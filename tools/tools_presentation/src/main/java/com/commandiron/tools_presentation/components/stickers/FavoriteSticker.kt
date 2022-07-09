package com.commandiron.tools_presentation.components.stickers

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.tools_presentation.R

@Composable
fun FavoriteSticker(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false
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
            painter = painterResource(
                id = if(isFavorite) {
                    R.drawable.favorite_filled
                } else {
                    R.drawable.favorite_outlined
                }
            ),
            contentDescription = null,
            modifier = Modifier.padding(spacing.spaceExtraSmall),
            tint = if(isFavorite) {
                Color.Unspecified
            } else {
                MaterialTheme.colorScheme.onTertiary
            }
        )
    }
}