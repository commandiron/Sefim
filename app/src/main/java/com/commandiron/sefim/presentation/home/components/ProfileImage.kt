package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.sefim.R

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = imageUrl)
                .apply(
                    block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        error(R.drawable.ic_blank_profile_picture)
                        fallback(R.drawable.ic_blank_profile_picture)
                    }
                )
                .build()
        ),
        contentDescription = null,
        modifier = modifier
            .clip(MaterialTheme.shapes.large),
        contentScale = ContentScale.Crop
    )
}