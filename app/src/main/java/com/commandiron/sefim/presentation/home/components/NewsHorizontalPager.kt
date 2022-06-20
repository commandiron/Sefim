
package com.commandiron.sefim.presentation.home.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.home.components.toolscomponents.ToolGridItem
import com.commandiron.sefim.presentation.model.NewsPresentation
import com.google.accompanist.pager.*

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
            news[page].steelPriceContentPresentation?.let {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.spaceLarge),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.secondary.copy(0.9f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = it.date + " " + it.title)
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .alignBy(LastBaseline),
                                text = it.Q8Price,
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .alignBy(LastBaseline),
                                text = "(Φ8)",
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Start
                            )
                        }
                        Text(
                            text = "Φ10 Fiyat: ${it.Q10Price}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Φ12-32 Fiyat: ${it.Q1232Price}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "(KDV Dahil, Nakliye Hariç)",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            news[page].newIconArrivedPresentation?.let {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.spaceLarge),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.secondary.copy(0.9f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = spacing.spaceSmall,
                                vertical = spacing.spaceMedium
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(spacing.spaceSmall))
                        ToolGridItem(
                            tool = it.toolPresentation,
                            onIconClick = { onClick() },
                            onIconLongClick = {}
                        )
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceMedium),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                inactiveColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                activeColor = MaterialTheme.colorScheme.background,
                indicatorHeight = 1.dp,
                indicatorWidth = 16.dp,
                indicatorShape = MaterialTheme.shapes.small
            )
        }
    }
}
