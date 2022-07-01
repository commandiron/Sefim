package com.commandiron.tools_presentation.aeratedConcTool.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OutlinedDropDown(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    Unit: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    onClick:() -> Unit,
    isExpanded: Boolean,
    items: List<String>?,
    onItemSelect:(Int) -> Unit,
    onDismissRequest:(Int?) -> Unit,
) {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.hasFocus) {
                        onClick()
                    }
                },
            value = value,
            readOnly = true,
            onValueChange = onValueChange,
            enabled = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false
            ),
            singleLine = true,
            textStyle = textStyle.copy(
                textAlign = TextAlign.Start
            ),
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            visualTransformation = ThousandSeparatorVisualTransformationWithAddedUnit(
                maxFractionDigits = 1,
                addedUnit = Unit
            ),
            shape = MaterialTheme.shapes.large
        )
        CustomDropDownMenu(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth(0.25f),
            isExpanded = isExpanded,
            dropDownItems = items,
            onSelect = {
                focusManager.clearFocus()
                onItemSelect(it)
            },
            onDismissRequest = {
                focusManager.clearFocus()
                onDismissRequest(it)
            }
        )
    }
}