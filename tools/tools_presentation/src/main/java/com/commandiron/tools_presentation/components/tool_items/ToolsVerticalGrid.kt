package com.commandiron.tools_presentation.components.tool_items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.tools_domain.model.Tool

@Composable
fun ToolsVerticalGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    tools: List<Tool>? = null,
    isWobbling: Boolean = false,
    showFavoriteIcon: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    columnCount: Int = 4,
    addToolIconVisible: Boolean = true,
    onIconClick: (Tool) -> Unit,
    onIconLongClick: () -> Unit,
    onFavorite: (Tool) -> Unit,
    onUnFavorite: (Tool) -> Unit,
    onAddClick: () -> Unit,
    onToLeft: (Tool) -> Unit,
    onToRight: (Tool) -> Unit
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
                        showFavoriteIcon = showFavoriteIcon,
                        isFavorite = tool.isFavorite,
                        onIconClick = { onIconClick(tool) },
                        onIconLongClick = onIconLongClick,
                        onFavorite = { onFavorite(tool) },
                        onUnFavorite = { onUnFavorite(tool) },
                        onToLeft = { onToLeft(tool) },
                        onToRight = { onToRight(tool) }
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