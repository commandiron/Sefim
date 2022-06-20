package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.navigation.BottomNavigation
import com.commandiron.sefim.navigation.defaultNavigationItems
import com.commandiron.sefim.presentation.home.components.NewsHorizontalPager
import com.commandiron.sefim.presentation.home.components.ProfileHeader
import com.commandiron.sefim.presentation.home.components.carousel.Carousel
import com.commandiron.sefim.presentation.home.components.carousel.CarouselDefaults
import com.commandiron.sefim.presentation.home.components.carousel.rememberCarouselScrollState
import com.commandiron.sefim.presentation.home.components.toolscomponents.ToolsRow
import com.commandiron.sefim.presentation.home.components.toolscomponents.ToolsVerticalGrid
import com.commandiron.sefim.presentation.model.defaultFavories
import com.commandiron.sefim.presentation.model.defaultNews
import com.commandiron.sefim.presentation.model.defaultTools
import com.commandiron.sefim.ui.theme.SefimTheme

@Composable
fun HomeScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = spacing.homeScreenPadding,
                top = spacing.homeScreenPadding,
                end = spacing.homeScreenPadding,
                bottom = 80.dp,
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
                val lazyGridState = rememberLazyGridState()
                Row {
                    ToolsVerticalGrid(
                        state = lazyGridState,
                        modifier = Modifier
                            .wrapContentHeight()
                            .heightIn(max = 180.dp),
                        tools = defaultFavories,
                        onIconClick = {},
                        onAddClick = {}
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = "Haberler - Yenilikler",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                NewsHorizontalPager(
                    modifier = Modifier.height(180.dp),
                    news = defaultNews
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
                    modifier = Modifier.heightIn(max = 90.dp),
                    tools = defaultTools,
                    onIconClick = {}
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Carousel(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    colors = CarouselDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.onSurface,
                        scrollingThumbColor = MaterialTheme.colorScheme.onSurface,
                        backgroundColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        BottomNavigation(navigationItems = defaultNavigationItems)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    SefimTheme() {
        HomeScreen()
    }
}