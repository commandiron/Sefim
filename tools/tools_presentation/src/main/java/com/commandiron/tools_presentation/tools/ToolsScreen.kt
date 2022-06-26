package com.commandiron.tools_presentation.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.tools_presentation.components.SearchTextField
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun ToolsScreen(
    viewModel: ToolsViewModel = hiltViewModel(),
    onIconClick: (Int) -> Unit
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.homeScreenPadding,
                top = spacing.spaceMedium,
                end = spacing.homeScreenPadding,
                bottom = spacing.bottomNavigationHeight
            ),
    ){
        var text by remember { mutableStateOf("")}
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            hint = "ARA",
            onChange = {text = it},
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        ToolsVerticalGrid(
            state = rememberLazyGridState(),
            textStyle = MaterialTheme.typography.bodySmall,
            tools = state.allTools,
            showFavoriteIcon = true,
            columnCount = 3,
            addToolIconVisible = false,
            onIconClick = { onIconClick(it.id) },
            onIconLongClick = {},
            onFavorite = { viewModel.onEvent(ToolsUserEvent.Favorite(it))},
            onUnFavorite = {},
            onAddClick = {}
        )
    }
}