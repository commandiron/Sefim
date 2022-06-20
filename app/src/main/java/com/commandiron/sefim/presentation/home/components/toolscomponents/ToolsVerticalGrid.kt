package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.home.components.carousel.rememberCarouselScrollState
import com.commandiron.sefim.presentation.model.ToolPresentation
import com.commandiron.sefim.presentation.model.ToolTag
import com.commandiron.sefim.presentation.model.defaultTools
import com.commandiron.sefim.ui.theme.SefimTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ToolsVerticalGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    tools: List<ToolPresentation>? = null,
    onIconClick: (ToolPresentation) -> Unit,
    onIconLongClick: () -> Unit,
    onUnFavorite: (ToolPresentation) -> Unit,
    onAddClick: () -> Unit,
    columnCount: Int = 4,
) {
    val spacing = LocalSpacing.current
    Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
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
                    ToolGridItemWithSticker(
                        tool = tool,
                        onIconClick = { onIconClick(tool) },
                        onIconLongClick = onIconLongClick,
                        onUnFavorite = { onUnFavorite(tool) }
                    )
                }
                item {
                    ToolGridItemAdd(onAddClick = onAddClick)
                }
            } ?: run {
                item {
                    ToolGridItemAdd(onAddClick = onAddClick)
                }
            }
        }
    }

}