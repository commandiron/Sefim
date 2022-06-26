package com.commandiron.tools_presentation.components.tool_items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.sefim.presentation.home.components.GridItemAdd
import com.commandiron.tools_presentation.model.ToolPresentation

@Composable
fun ToolsVerticalGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    tools: List<ToolPresentation>? = null,
    isWobbling: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    columnCount: Int = 4,
    addToolIconVisible: Boolean = true,
    onIconClick: (ToolPresentation) -> Unit,
    onIconLongClick: () -> Unit,
    onUnFavorite: (ToolPresentation) -> Unit,
    onAddClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.large
    ) {
        LazyVerticalGrid(
            modifier = modifier,
            state = state,
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(spacing.spaceSmall),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
        ){
            tools?.let {
                items(it){ tool ->
                    ToolItemWithSticker(
                        tool = tool,
                        textStyle = textStyle,
                        isWobbling = isWobbling,
                        onIconClick = { onIconClick(tool) },
                        onIconLongClick = onIconLongClick,
                        onUnFavorite = { onUnFavorite(tool) }
                    )
                }
                item {
                    if(addToolIconVisible){
                        GridItemAdd(onAddClick = onAddClick)
                    }
                }
            } ?: run {
                item {
                    if(addToolIconVisible){
                        GridItemAdd(onAddClick = onAddClick)
                    }
                }
            }
        }
    }

}