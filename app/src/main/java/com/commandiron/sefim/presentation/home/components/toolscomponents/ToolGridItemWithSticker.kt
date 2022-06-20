package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.commandiron.sefim.presentation.model.ToolPresentation
import com.commandiron.sefim.presentation.model.ToolTag

@Composable
fun ToolGridItemWithSticker(
    tool: ToolPresentation,
    onIconClick: () -> Unit,
    onIconLongClick: () -> Unit,
    onUnFavorite: () -> Unit,
) {
    val iconRotateAnim = remember { Animatable(0f) }
    LaunchedEffect(key1 = tool.onUnFavoriteState){
        if(tool.onUnFavoriteState){
            iconRotateAnim.animateTo(-5f)
            iconRotateAnim.animateTo(
                targetValue = 5f,
                animationSpec = infiniteRepeatable(
                    tween(180),
                    RepeatMode.Reverse
                )
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .rotate(iconRotateAnim.value)
    ) {
        ToolGridItem(
            tool = tool,
            onIconClick = onIconClick,
            onIconLongClick = onIconLongClick
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
                    .clickable { onIconClick() }
            )
        }
        if (tool.toolTags.contains(ToolTag.CAM)) {
            CamSticker(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxSize(0.35f)
            )
        }
        if(tool.toolTags.contains(ToolTag.SOON)){
            SoonSticker(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )
        }
        if (tool.onUnFavoriteState) {
            UnFavoriteSticker(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize(0.35f)
                    .clickable{ onUnFavorite() }
            )
        }

    }
}