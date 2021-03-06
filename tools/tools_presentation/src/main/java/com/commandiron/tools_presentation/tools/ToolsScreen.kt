package com.commandiron.tools_presentation.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.Strings.Turkish.SEARCH
import com.commandiron.core_ui.util.WindowInfo
import com.commandiron.tools_presentation.components.SearchTextField
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun ToolsScreen(
    viewModel: ToolsViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
    showSnackbar: (String) -> Unit
) {
    val localSoftwareKeyboard = LocalSoftwareKeyboardController.current
    val windowTypeInfo = LocalWindowTypeInfo.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> navigateTo(event.route)
                is UiEvent.ShowSnackbar -> showSnackbar(event.message)
                is UiEvent.HideKeyboard -> {
                    localSoftwareKeyboard?.hide()
                }
                else -> {}
            }
        }
    }
    if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        ToolsCompactContent(viewModel)
    }else{
        ToolsExpandedContent(viewModel)
    }
}

@Composable
fun ToolsCompactContent(viewModel: ToolsViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact),
    ){
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.searchText,
            hint = SEARCH.uppercase(),
            onChange = {viewModel.onEvent(ToolsUserEvent.SearchChange(it))},
            onDone = {viewModel.onEvent(ToolsUserEvent.OnKeyboardDone)}
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
            onAddClick = {},
            onToLeft = {},
            onToRight = {}
        )
    }
}
@Composable
fun ToolsExpandedContent(viewModel: ToolsViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForExpanded),
    ){
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.searchText,
            hint = SEARCH.uppercase(),
            onChange = {viewModel.onEvent(ToolsUserEvent.SearchChange(it))},
            onDone = {viewModel.onEvent(ToolsUserEvent.OnKeyboardDone)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        ToolsVerticalGrid(
            state = rememberLazyGridState(),
            textStyle = MaterialTheme.typography.bodySmall,
            tools = state.filteredTools,
            showFavoriteIcon = true,
            columnCount = 6,
            addToolIconVisible = false,
            onIconClick = { viewModel.onEvent(ToolsUserEvent.IconClick(it)) },
            onIconLongClick = {},
            onFavorite = { viewModel.onEvent(ToolsUserEvent.Favorite(it))},
            onUnFavorite = {},
            onAddClick = {},
            onToLeft = {},
            onToRight = {}
        )
    }
}