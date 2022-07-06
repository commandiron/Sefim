package com.commandiron.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun CustomDropDownMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    dropDownItems: List<String>?,
    onSelect:(String) -> Unit,
    onDismissRequest:(Int?) -> Unit,
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    DropdownMenu(
        modifier = modifier,
        expanded = isExpanded,
        onDismissRequest = {
            onDismissRequest(selectedIndex)
        },
    ) {
        dropDownItems?.forEachIndexed{ index, title->
            var enabled by remember { mutableStateOf(false)}
            enabled = index == selectedIndex
            DropdownMenuItem(
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
                text = {
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                onClick = {
                    enabled = !enabled
                    selectedIndex = index
                    selectedIndex?.let {
                        onSelect(title)
                    }
                },
                trailingIcon = {
                    Text(
                        text = " cm",
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                enabled = !enabled,
                colors = MenuDefaults.itemColors(
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    trailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}