package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.model.ToolPresentation
import com.commandiron.sefim.presentation.model.ToolTag
import com.commandiron.sefim.presentation.model.defaultTools
import com.commandiron.sefim.ui.theme.SefimTheme

@Composable
fun ToolsVerticalGrid(
    state: LazyGridState,
    modifier: Modifier = Modifier,
    tools: List<ToolPresentation>? = null,
    onIconClick: (ToolPresentation) -> Unit,
    onAddClick: () -> Unit,
    columnCount: Int = 4,
) {
    val spacing = LocalSpacing.current
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ToolGridItem(
                        tool = tool,
                        onIconClick = onIconClick
                    )
                    if(tool.toolTags.contains(ToolTag.NEW)){
                        NewSticker(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )
                    }
                    if(tool.toolTags.contains(ToolTag.CAM)){
                        CamSticker(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .fillMaxSize(0.35f)
                        )
                    }
                }
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

@Preview
@Composable
fun PreviewToolsVerticalGrid() {
    SefimTheme() {
        ToolsVerticalGrid(
            state = rememberLazyGridState(),
            tools = defaultTools,
            onAddClick = {},
            onIconClick = {},
            columnCount = 4
        )
    }
}