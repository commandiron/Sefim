package com.commandiron.tools_presentation.aeratedConcTool.components

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign


@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    value: String,
    onValueChange:(String) -> Unit,
    label: String,
    symbol: String,
    onDone:() -> Unit,
) {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            autoCorrect = false
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
                focusManager.clearFocus()
            }
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
        visualTransformation = ThousandSeparatorVisualTransformationWithAddedSymbol(
            maxFractionDigits = 2,
            addedSymbol = symbol
        ),
        shape = MaterialTheme.shapes.large
    )
}