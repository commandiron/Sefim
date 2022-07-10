package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import com.commandiron.core_ui.util.Strings.Turkish.DAILY_IRON_PRICE
import com.commandiron.news_domain.model.News
import com.commandiron.rebarpricestool_presentation.components.RebarPriceItem
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_presentation.components.tool_items.ToolItemWithSticker
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@Composable
fun NewsHorizontalPager(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    hasError: Boolean = false,
    rebarPrice: RebarPrice?,
    newTool: Tool?,
    newsList: List<News>?,
    bodyTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onRebarPriceClick: () -> Unit,
    onNewToolClick: (Tool) -> Unit,
    onNewsClick:(News) -> Unit,
    refresh:() -> Unit
) {
    val spacing = LocalSpacing.current
    val pagerState = rememberPagerState()
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.background
    ) {
        var pagerCount = 2
        newsList?.let {
            pagerCount += it.size
        }
        HorizontalPager(
            state = pagerState,
            count = pagerCount,
            contentPadding = PaddingValues(vertical = spacing.spaceSmall)
        ) { page ->
            Surface(
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .fillMaxHeight()
                    .fillMaxWidth()
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
                if(hasError){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(
                            onClick = { refresh() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if(isLoading){
                        CircularProgressIndicator()
                    }else{
                        when (page) {
                            0 -> {
                                rebarPrice?.let {
                                    RebarPriceContent(
                                        rebarPrice = it,
                                        bodyTextStyle = bodyTextStyle,
                                        onClick = onRebarPriceClick
                                    )
                                }
                            }
                            1 -> {
                                newTool?.let {
                                    NewToolContent(
                                        newTool = it,
                                        onClick = onNewToolClick
                                    )
                                }
                            }
                            else -> {
                                newsList?.let {
                                    NewsContent(
                                        news = it[page - 2],
                                        onClick = onNewsClick
                                    )
                                }
                            }
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
    bodyTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(0.25f),
                shape = MaterialTheme.shapes.small
            )
            .fillMaxSize(0.9f)
            .padding(spacing.spaceExtraSmall)
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = DAILY_IRON_PRICE,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.background
        )
        RebarPriceItem(
            rebarPrice = rebarPrice,
            textStyle = bodyTextStyle
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
                color = MaterialTheme.colorScheme.onBackground.copy(0.25f),
                shape = MaterialTheme.shapes.small
            )
            .fillMaxSize(0.9f)
            .padding(spacing.spaceSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Yeni Aracımız Çıktı",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        ToolItemWithSticker(
            modifier = Modifier.padding(spacing.spaceSmall),
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