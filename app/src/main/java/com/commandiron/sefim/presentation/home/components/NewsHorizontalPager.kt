
package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.model.NewsPresentation
import com.google.accompanist.pager.*

@Composable
fun NewsHorizontalPager(
    news: List<NewsPresentation>,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val pagerState = rememberPagerState()
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        HorizontalPager(
            state = pagerState,
            count = news.size
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = news[page].backgroundImageUrl)
                        .build()
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceMedium),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                inactiveColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                activeColor = MaterialTheme.colorScheme.surface,
                indicatorHeight = 1.dp,
                indicatorWidth = 16.dp,
                indicatorShape = MaterialTheme.shapes.small
            )
        }
    }
}