package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.model.ToolPresentation
import com.commandiron.sefim.presentation.model.ToolTag
import com.commandiron.sefim.presentation.model.defaultTools

@Composable
fun ToolsRow(
    modifier: Modifier = Modifier,
    state: LazyListState,
    tools: List<ToolPresentation>? = null,
    onIconClick: (ToolPresentation) -> Unit,
) {
    val spacing = LocalSpacing.current
    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(spacing.spaceSmall),
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        verticalAlignment = Alignment.CenterVertically
    ){
        tools?.let {
            items(it){ tool ->
                ToolGridItemWithSticker(
                    tool = tool,
                    onIconClick = { onIconClick(tool) },
                    onIconLongClick = {},
                    onUnFavorite = {}
                )
            }
        }
    }
}