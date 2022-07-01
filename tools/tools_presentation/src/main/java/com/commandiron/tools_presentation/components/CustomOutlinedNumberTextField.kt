package com.commandiron.tools_presentation.components

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
fun CustomOutlinedNumberTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    value: String,
    onValueChange:(String) -> Unit,
    onDone:() -> Unit,
    label: String,
    Unit: String,
    forceValueInt: Boolean = false
) {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = {
            if(forceValueInt){
                if(it.toIntOrNull() != null || it.isEmpty()){
                    onValueChange(it)
                }
            }else{
                if(it.toDoubleOrNull() != null || it.isEmpty()){
                    onValueChange(it)
                }
            }
        },
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
        visualTransformation = ThousandSeparatorVisualTransformationWithAddedUnit(
            maxFractionDigits = 2,
            addedUnit = Unit
        ),
        shape = MaterialTheme.shapes.large
    )
}