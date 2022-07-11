package com.commandiron.tools_presentation.components.tool_items

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_presentation.components.stickers.*

@Composable
fun ToolItemWithSticker(
    modifier: Modifier = Modifier,
    tool: Tool,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    isWobbling: Boolean = false,
    showFavoriteIcon: Boolean = false,
    isFavorite: Boolean = false,
    onIconClick: () -> Unit,
    onIconLongClick: () -> Unit,
    onFavorite: () -> Unit,
    onUnFavorite: () -> Unit,
    onToLeft: () -> Unit,
    onToRight: () -> Unit,
) {
    val iconRotateAnim = remember { Animatable(0f) }
    LaunchedEffect(key1 = isWobbling){
        if(isWobbling){
            iconRotateAnim.animateTo(-5f, animationSpec = tween(280))
            iconRotateAnim.animateTo(
                targetValue = 5f,
                animationSpec = infiniteRepeatable(
                    tween(280),
                    RepeatMode.Reverse
                )
            )
        }else{
            iconRotateAnim.animateTo(0f, animationSpec = tween(280))
        }
    }
    Box(
        modifier = modifier
            .rotate(iconRotateAnim.value)
            .combinedClickable(
                onClick = {
                    if(!isWobbling){
                        onIconClick()
                    }
                },
                onLongClick = onIconLongClick
            )
    ) {
        ToolItem(
            tool = tool,
            textStyle = textStyle
        )
        if(isWobbling) {
            UnFavoriteSticker(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize(0.35f)
                    .clickable { onUnFavorite() }
            )
            ToLeftSticker(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxSize(0.35f)
                    .clickable { onToLeft() }
            )
            ToRightSticker(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxSize(0.35f)
                    .clickable { onToRight() }
            )
        }else{
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
            if (tool.toolTags.contains(ToolTag.AR)) {
                ArSticker(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxSize(0.35f)
                )
            }
            if(tool.toolTags.contains(ToolTag.SOON)){
                SoonSticker(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
            if(showFavoriteIcon){
                FavoriteSticker(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .fillMaxSize(0.25f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ){
                            onFavorite()
                         },
                    isFavorite = isFavorite
                )
            }
        }
    }
}