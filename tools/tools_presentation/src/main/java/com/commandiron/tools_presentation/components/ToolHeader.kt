package com.commandiron.tools_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.LocalSpacing

@Composable
fun ToolHeader(
    title: String,
    onIconClick:() -> Unit,
) {
    val spacing = LocalSpacing.current
    Row() {
        Icon(
            modifier = Modifier
                .clickable { onIconClick() }
                .alignBy(LastBaseline),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(spacing.spaceLarge))
        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}