package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.LocalSystemUiController
import com.commandiron.core_ui.components.carousel.Carousel
import com.commandiron.core_ui.components.carousel.CarouselDefaults
import com.commandiron.sefim.presentation.home.components.*
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
) {
    val spacing = LocalSpacing.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val systemUiController = LocalSystemUiController.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.NavigateTo -> { navigateTo(event.route) }
            }
        }
    }
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.primary
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.onBackground
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                viewModel.onEvent(HomeUserEvent.SpaceClick)
            }
    ) {
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
                    .heightIn(max = screenHeightDp / 3f),
                state = lazyGridState,
                tools = state.favoriteTools,
                isWobbling = state.isFavoriteIconsWobbling,
                onIconClick = { viewModel.onEvent(HomeUserEvent.ToolClick) },
                onAddClick = { viewModel.onEvent(HomeUserEvent.AddToolClick) },
                onIconLongClick = { viewModel.onEvent(HomeUserEvent.ToolLongClick) },
                onUnFavorite = { viewModel.onEvent(HomeUserEvent.UnFavoriteClick) }
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = "Haberler - Yenilikler",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            NewsHorizontalPager(
                modifier = Modifier
                    .heightIn(max = screenHeightDp / 4f),
                newsContent = state.newsContent,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = "Önerilen Araçlar",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            val lazyListState = rememberLazyListState()
            ToolsRow(
                modifier = Modifier
                    .heightIn(max = screenHeightDp / 7f),
                state = lazyListState,
                tools = state.recommendedTools,
                onIconClick = { viewModel.onEvent(HomeUserEvent.ToolClick) },
            )
            Carousel(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                colors = CarouselDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    scrollingThumbColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(0.2f)
                )
            )
        }
    }

}