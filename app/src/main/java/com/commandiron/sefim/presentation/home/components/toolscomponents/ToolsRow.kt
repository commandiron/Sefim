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
                    if(tool.toolTags.contains(ToolTag.LOCKED)){
                        LockedSticker(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize(0.35f)
                        )
                    }
                    if (tool.toolTags.contains(ToolTag.CAM)) {
                        CamSticker(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .fillMaxSize(0.35f)
                        )
                    }
                }
            }
            item {
                Text(text = "Devamı İçin Tıklayın")
            }
        }
    }
}