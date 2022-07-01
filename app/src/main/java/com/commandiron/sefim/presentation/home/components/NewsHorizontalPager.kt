package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.news_domain.model.*
import com.commandiron.sefim.presentation.home.model.HomeNews
import com.commandiron.tools_domain.model.RebarPrice
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_presentation.components.tool_items.ToolItem
import com.commandiron.tools_presentation.components.tool_items.ToolItemWithSticker
import com.google.accompanist.pager.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun NewsHorizontalPager(
    modifier: Modifier = Modifier,
    homeNews: HomeNews,
    onRebarPriceClick: () -> Unit,
    onNewToolClick: (Tool) -> Unit,
    onNewsClick:(News) -> Unit
) {
    val spacing = LocalSpacing.current
    val pagerState = rememberPagerState()
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.background
    ) {
        val pagerCount = 2 + homeNews.newsList.size
        HorizontalPager(
            state = pagerState,
            count = pagerCount,
            contentPadding = PaddingValues(vertical = spacing.spaceSmall)
        ) { page ->
            Surface(
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .fillMaxHeight()
                    .fillMaxWidth(0.85f)
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
                ,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.primaryContainer,
                shadowElevation = spacing.spaceSmall
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = "https://www.ie.edu/insights/wp-content/uploads/2020/11/VanSchendel-Construction.jpg")
                            .build()
                    ),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (page) {
                        0 -> {
                            RebarPriceContent(
                                rebarPrice = homeNews.rebarPrice,
                                onClick = onRebarPriceClick
                            )
                        }
                        1 -> {
                            NewToolContent(
                                newTool = homeNews.newTool,
                                onClick = onNewToolClick
                            )
                        }
                        else -> {
                            NewsContent(
                                news = homeNews.newsList[page - 2],
                                onClick = onNewsClick
                            )
                        }
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
                inactiveColor = MaterialTheme.colorScheme.primary.copy(0.2f),
                activeColor = MaterialTheme.colorScheme.primary,
                indicatorHeight = 1.dp,
                indicatorWidth = 16.dp,
                indicatorShape = MaterialTheme.shapes.small
            )
        }
    }
}

@Composable
fun RebarPriceContent(
    rebarPrice: RebarPrice,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer.copy(0.8f),
                shape = MaterialTheme.shapes.small
            )
            .fillMaxSize(0.75f)
            .padding(spacing.spaceSmall)
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = rebarPrice.date,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = rebarPrice.city + " " + "Demir Fiyatları",
            style = MaterialTheme.typography.bodyLarge
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.alignBy(LastBaseline),
                text = "Φ8: ",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.alignBy(LastBaseline),
                text = rebarPrice.q8mmPrice,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Text(
            text = "Φ10: " + rebarPrice.q10mmPrice,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Φ12-32: " + rebarPrice.q1232mmPrice,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Composable
fun NewToolContent(
    newTool: Tool,
    onClick: (Tool) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer.copy(0.8f),
                shape = MaterialTheme.shapes.small
            )
            .fillMaxSize(0.75f)
            .padding(spacing.spaceSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Yeni Aracımız Çıktı")
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        ToolItemWithSticker(
            tool = newTool,
            onIconClick = { onClick(newTool) },
            onIconLongClick = {},
            onFavorite = {},
            onUnFavorite = {}
        )
    }

}
@Composable
fun NewsContent(
    news: News,
    onClick: (News) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer.copy(0.8f),
                shape = MaterialTheme.shapes.small
            )
            .fillMaxSize(0.75f)
            .padding(spacing.spaceSmall)
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null) {
                onClick(news)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = news.title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Haberin Devamını Oku ->",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Blue,
            textAlign = TextAlign.Center
        )
    }
}