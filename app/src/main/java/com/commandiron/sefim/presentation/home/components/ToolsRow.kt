package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_presentation.components.tool_items.ToolItemWithSticker

@Composable
fun ToolsRow(
    modifier: Modifier = Modifier,
    state: LazyListState,
    tools: List<Tool>,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    onIconClick: (Tool) -> Unit,
    onFavorite: (Tool) -> Unit,
) {
    val spacing = LocalSpacing.current
    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(spacing.spaceSmall),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        verticalAlignment = Alignment.CenterVertically
    ){
        items(tools){ tool ->
            ToolItemWithSticker(
                tool = tool,
                textStyle = textStyle,
                showFavoriteIcon = true,
                onIconClick = { onIconClick(tool) },
                onIconLongClick = {},
                onFavorite = { onFavorite(tool) },
                onUnFavorite = {},
                onToLeft = {},
                onToRight = {}
            )
        }
    }
}