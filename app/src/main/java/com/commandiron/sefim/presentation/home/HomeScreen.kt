package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.core.LocalSystemUiController
import com.commandiron.sefim.presentation.home.components.NewsHorizontalPager
import com.commandiron.sefim.presentation.home.components.ProfileHeader
import com.commandiron.sefim.presentation.home.components.carousel.Carousel
import com.commandiron.sefim.presentation.home.components.carousel.CarouselDefaults
import com.commandiron.sefim.presentation.home.components.toolscomponents.ToolsRow
import com.commandiron.sefim.presentation.home.components.toolscomponents.ToolsVerticalGrid
import com.commandiron.sefim.presentation.model.defaultNews
import com.commandiron.sefim.presentation.model.defaultTools

@Composable
fun HomeScreen() {
    val spacing = LocalSpacing.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val systemUiController = LocalSystemUiController.current
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.onBackground
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.homeScreenPadding,
                top = spacing.homeScreenPadding,
                end = spacing.homeScreenPadding
            )
    ) {
        ProfileHeader(
            imageUrl = "",
            onEditClick = {},
            onNotificationClick = {}
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        LazyColumn {
            item {
                Text(
                    text = "Favori Araçlarım",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row {
                    ToolsVerticalGrid(
                        modifier = Modifier
                            .wrapContentHeight()
                            .heightIn(max = screenHeightDp / 4),
                        state = rememberLazyGridState(),
                        tools = null,
                        onIconClick = {},
                        onAddClick = {},
                        onIconLongClick = {},
                        onUnFavorite = {}
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = "Haberler - Yenilikler",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                NewsHorizontalPager(
                    modifier = Modifier
                        .heightIn(max = screenHeightDp / 4),
                    news = defaultNews,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = "Önerilen Araçlar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                val lazyListState = rememberLazyListState()
                ToolsRow(
                    state = lazyListState,
                    modifier = Modifier
                        .heightIn(max = screenHeightDp / 7),
                    tools = defaultTools,
                    onIconClick = {},
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Carousel(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    colors = CarouselDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.onBackground,
                        scrollingThumbColor = MaterialTheme.colorScheme.onBackground,
                        backgroundColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    }
}