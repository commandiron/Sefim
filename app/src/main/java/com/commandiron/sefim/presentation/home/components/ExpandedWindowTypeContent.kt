package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings
import com.commandiron.sefim.presentation.home.HomeUserEvent
import com.commandiron.sefim.presentation.home.HomeViewModel
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun ExpandedWindowTypeContent(
    viewModel: HomeViewModel
) {
    val spacing = LocalSpacing.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(spacing.defaultScreenPaddingForExpanded),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileHeader(
            imageUrl = state.profileImageUrl,
            onProfilePictureClick = { viewModel.onEvent(HomeUserEvent.ProfilePictureClick) },
            onEditClick = { viewModel.onEvent(HomeUserEvent.EditClick) },
            onNotificationClick = { viewModel.onEvent(HomeUserEvent.NotificationClick) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Row(
            modifier = Modifier.height(screenHeightDp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
            }
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${Strings.Turkish.NEWS} - ${Strings.Turkish.INNOVATIONS}",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                NewsHorizontalPager(
                    modifier = Modifier,
                    isLoading = state.newsIsLoading,
                    hasError = state.newsHasError,
                    homeNews = state.homeNews,
                    bodyTextStyle = MaterialTheme.typography.labelLarge,
                    onRebarPriceClick = { viewModel.onEvent(HomeUserEvent.RebarPriceClick) },
                    onNewToolClick = { viewModel.onEvent(HomeUserEvent.NewToolClick(it)) },
                    onNewsClick = { viewModel.onEvent(HomeUserEvent.NewsClick(it)) },
                    refresh = { viewModel.onEvent(HomeUserEvent.NewsRefresh) }
                )
            }
        }
    }
}