package com.commandiron.tools_presentation.components.tool_items

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.tools_domain.model.ToolPresentation

@Composable
fun ToolItem(
    modifier: Modifier = Modifier,
    tool: ToolPresentation,
    textStyle: TextStyle
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .aspectRatio(1f),
        shape = MaterialTheme.shapes.large,
        shadowElevation = spacing.spaceSmall,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceExtraSmall),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.8f),
                    painter = painterResource(id = tool.resources),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Text(
                text = tool.title,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = textStyle.copy(fontWeight = FontWeight.Bold),
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
                textAlign = TextAlign.Center
            )
        }
    }
}

