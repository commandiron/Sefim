package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.news_presentation.model.NewsPresentation
import com.commandiron.core_ui.LocalSpacing
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@Composable
fun NewsHorizontalPager(
    news: List<NewsPresentation>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val pagerState = rememberPagerState()
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.background
    ) {
        HorizontalPager(
            state = pagerState,
            count = news.size
        ) { page ->
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.75f)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        val fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        val start = 0.65f
                        val stop = 1f

                        val scaleLerp = (1 - fraction) * start + fraction * stop

                        scaleX = scaleLerp
                        scaleY = scaleLerp

                        val startAlpha = 0.3f
                        val stopAlpha = 1f
                        val fractionAlpha = 1f - pageOffset.coerceIn(0f, 1f)

                        val alphaLerp = (1 - fractionAlpha) * startAlpha + fractionAlpha * stopAlpha

                        alpha = alphaLerp
                    }
                    .clickable { onClick() }
                ,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f)
                            .background(MaterialTheme.colorScheme.primary ),
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

            }

//            news[page].steelPriceContentPresentation?.let {
//                Surface(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(spacing.spaceLarge),
//                    shape = MaterialTheme.shapes.large,
//                    color = MaterialTheme.colorScheme.primaryContainer.copy(0.9f)
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = it.date + " " + it.title)
//                        Row(
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            Spacer(modifier = Modifier.weight(1f))
//                            Text(
//                                modifier = Modifier
//                                    .weight(2f)
//                                    .alignBy(LastBaseline),
//                                text = it.Q8Price,
//                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .alignBy(LastBaseline),
//                                text = "(Φ8)",
//                                style = MaterialTheme.typography.bodySmall,
//                                textAlign = TextAlign.Start
//                            )
//                        }
//                        Text(
//                            text = "Φ10 Fiyat: ${it.Q10Price}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Text(
//                            text = "Φ12-32 Fiyat: ${it.Q1232Price}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Text(
//                            text = "(KDV Dahil, Nakliye Hariç)",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }
//                }
//            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceSmall),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                inactiveColor = MaterialTheme.colorScheme.primaryContainer,
                activeColor = MaterialTheme.colorScheme.primary,
                indicatorHeight = 1.dp,
                indicatorWidth = 16.dp,
                indicatorShape = MaterialTheme.shapes.small
            )
        }
    }
}
