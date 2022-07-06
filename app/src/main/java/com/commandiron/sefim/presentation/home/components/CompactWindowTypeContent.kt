package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.components.carousel.Carousel
import com.commandiron.core_ui.components.carousel.CarouselDefaults
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings
import com.commandiron.sefim.presentation.home.HomeUserEvent
import com.commandiron.sefim.presentation.home.HomeViewModel
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun CompactWindowTypeContent(
    viewModel: HomeViewModel
) {
    val spacing = LocalSpacing.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(spacing.defaultScreenPaddingForCompact),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileHeader(
            imageUrl = state.profileImageUrl,
            onProfilePictureClick = { viewModel.onEvent(HomeUserEvent.ProfilePictureClick) },
            onEditClick = { viewModel.onEvent(HomeUserEvent.EditClick) },
            onNotificationClick = { viewModel.onEvent(HomeUserEvent.NotificationClick) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = Strings.Turkish.MY_FAVORITE_TOOLS,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        val lazyGridState = rememberLazyGridState()
        ToolsVerticalGrid(
            state = lazyGridState,
            tools = state.favoriteTools,
            isWobbling = state.isFavoriteIconsWobbling,
            onIconClick = { viewModel.onEvent(HomeUserEvent.IconClick(it)) },
            onAddClick = { viewModel.onEvent(HomeUserEvent.AddClick) },
            onIconLongClick = { viewModel.onEvent(HomeUserEvent.ToolLongClick) },
            onFavorite = {},
            onUnFavorite = { viewModel.onEvent(HomeUserEvent.UnFavoriteClick(it)) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = "${Strings.Turkish.NEWS} - ${Strings.Turkish.INNOVATIONS}",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NewsHorizontalPager(
            modifier = Modifier
                .heightIn(max = screenHeightDp / 4f),
            isLoading = state.newsIsLoading,
            hasError = state.newsHasError,
            rebarPrice = state.rebarPrice,
            newTool = state.newTool,
            newsList = state.newsList,
            onRebarPriceClick = { viewModel.onEvent(HomeUserEvent.RebarPriceClick) },
            onNewToolClick = { viewModel.onEvent(HomeUserEvent.NewToolClick(it)) },
            onNewsClick = { viewModel.onEvent(HomeUserEvent.NewsClick(it)) },
            refresh = { viewModel.onEvent(HomeUserEvent.NewsRefresh) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        state.recommendedTools?.let { recommendedTools ->
            if(recommendedTools.isNotEmpty()){
                Text(
                    text = Strings.Turkish.RECOMMENDED_TOOLS,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
                val lazyListState = rememberLazyListState()
                ToolsRow(
                    modifier = Modifier
                        .heightIn(max = screenHeightDp / 7f),
                    state = lazyListState,
                    tools = recommendedTools,
                    onIconClick = { viewModel.onEvent(HomeUserEvent.IconClick(it)) },
                    onFavorite = { viewModel.onEvent(HomeUserEvent.FavoriteClick(it)) }
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
}