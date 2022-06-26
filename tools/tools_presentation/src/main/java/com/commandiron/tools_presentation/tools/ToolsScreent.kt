package com.commandiron.tools_presentation.tools

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.tools_presentation.components.SearchTextField
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid
import com.commandiron.tools_presentation.model.defaultTools

@Composable
fun ToolsScreen() {
    val spacing = LocalSpacing.current
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
            tools = defaultTools,
            columnCount = 3,
            addToolIconVisible = false,
            onIconClick = {},
            onIconLongClick = {},
            onUnFavorite = {},
            onAddClick = {}
        )
    }
}