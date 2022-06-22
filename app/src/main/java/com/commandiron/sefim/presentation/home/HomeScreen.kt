package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.commandiron.news_presentation.model.defaultNews
import com.commandiron.tools_presentation.model.defaultTools
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.LocalSystemUiController
import com.commandiron.core_ui.components.AppLogo
import com.commandiron.core_ui.components.AppLogoWithName
import com.commandiron.core_ui.components.carousel.Carousel
import com.commandiron.core_ui.components.carousel.CarouselDefaults
import com.commandiron.core_ui.components.carousel.rememberCarouselScrollState
import com.commandiron.sefim.presentation.home.components.*
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid
import com.commandiron.tools_presentation.model.defaultFavorites

@Composable
fun HomeScreen() {
    val spacing = LocalSpacing.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val systemUiController = LocalSystemUiController.current
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.primary
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.onBackground
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.homeScreenPadding,
                top = spacing.spaceMedium,
                end = spacing.homeScreenPadding,
                bottom = spacing.bottomNavigationHeight
            )
    ) {
        ProfileHeader(
            imageUrl = "",
            onEditClick = {},
            onNotificationClick = {}
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = "Favori Araçlarım",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        val lazyGridState = rememberLazyGridState()
        ToolsVerticalGrid(
            modifier = Modifier
                .wrapContentHeight()
                .heightIn(max = screenHeightDp / 4.5f),
            state = lazyGridState,
            tools = defaultFavorites,
            onIconClick = {},
            onAddClick = {},
            onIconLongClick = {},
            onUnFavorite = {}
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = "Haberler - Yenilikler",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NewsHorizontalPager(
            modifier = Modifier
                .heightIn(max = screenHeightDp / 3f),
            news = defaultNews,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = "Önerilen Araçlar",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        val lazyListState = rememberLazyListState()
        ToolsRow(
            state = lazyListState,
            tools = defaultTools,
            onIconClick = {},
        )
        Carousel(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            colors = CarouselDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                scrollingThumbColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}