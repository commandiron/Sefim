package com.commandiron.tools_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.util.LocalSpacing

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    height: Dp = 64.dp,
    text: String,
    hint: String,
    onChange:(String) -> Unit = {}
) {
    val spacing = LocalSpacing.current
    BasicTextField(
        modifier = modifier
            .height(height)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.background
            )
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(percent = 50)
            ),
        value = text,
        onValueChange = { onChange(it) },
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.titleMedium,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier.padding(horizontal = height/3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty())
                        Text(
                            text = hint,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                            )
                        )
                    innerTextField()
                }
            }
        }
    )
}