package com.commandiron.sefim.presentation.home.components.toolscomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.commandiron.sefim.R
import com.commandiron.sefim.core.LocalSpacing
import com.commandiron.sefim.presentation.model.ToolPresentation
import com.commandiron.sefim.ui.theme.SefimTheme

@Composable
fun ToolGridItem(
    modifier: Modifier = Modifier,
    tool: ToolPresentation,
    onIconClick: (ToolPresentation) -> Unit,
    fontSize: TextUnit = 8.sp,
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .aspectRatio(1f),
        shape = MaterialTheme.shapes.large,
        shadowElevation = spacing.spaceExtraSmall,
        color = tool.iconBackground ?: MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.clickable { onIconClick(tool) },
        ) {
            Column(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.8f),
                        painter = painterResource(id = tool.resources),
                        contentDescription = null,
                        tint = tool.iconTint ?: MaterialTheme.colorScheme.onSurface
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tool.title,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = fontSize
                        ),
                        color = tool.titleColor ?: MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewToolGridItem() {
    SefimTheme() {
        ToolGridItem(
            tool = ToolPresentation(
                title = "Duvar Metraj Hesaplayıcı",
                resources = R.drawable.tool_wall
            ),
            onIconClick = {})
    }
}

