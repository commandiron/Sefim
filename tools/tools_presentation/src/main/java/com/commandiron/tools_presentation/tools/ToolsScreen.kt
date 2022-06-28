package com.commandiron.tools_presentation.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.tools_presentation.components.SearchTextField
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun ToolsScreen(
    viewModel: ToolsViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> navigateTo(event.route)
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.defaultHorizontalScreenPadding,
                top = spacing.spaceMedium,
                end = spacing.defaultHorizontalScreenPadding,
                bottom = spacing.bottomNavigationHeight
            ),
    ){
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.searchText,
            hint = "ARA",
            onChange = {viewModel.onEvent(ToolsUserEvent.SearchChange(it))},
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        ToolsVerticalGrid(
            state = rememberLazyGridState(),
            textStyle = MaterialTheme.typography.bodySmall,
            tools = state.filteredTools,
            showFavoriteIcon = true,
            columnCount = 3,
            addToolIconVisible = false,
            onIconClick = { viewModel.onEvent(ToolsUserEvent.IconClick(it)) },
            onIconLongClick = {},
            onFavorite = { viewModel.onEvent(ToolsUserEvent.Favorite(it))},
            onUnFavorite = {},
            onAddClick = {}
        )
    }
}