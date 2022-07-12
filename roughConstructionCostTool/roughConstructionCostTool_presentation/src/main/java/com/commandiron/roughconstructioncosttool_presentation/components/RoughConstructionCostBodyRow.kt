package com.commandiron.roughconstructioncosttool_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.commandiron.core_ui.components.CustomOutlinedNumberTextField
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.tools_presentation.components.ThousandSeparatorVisualTransformationWithAddedUnit

@Composable
fun RoughConstructionCostBodyRow(
    firstValue: String,
    onFirstValueChange:(String) -> Unit,
    firstValueLabel: String,
    firstValueUnit: String,
    secondValue: String,
    onSecondValueChange:(String) -> Unit,
    secondValueLabel: String,
    secondValueUnit: String,
    resultText: String,
    onDone:() -> Unit
) {
    val spacing = LocalSpacing.current
    Column() {
        Row(
            modifier = Modifier
                .height(spacing.spaceExtraLarge)
                .fillMaxWidth()
        ) {
            CustomOutlinedNumberTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                value = firstValue,
                onValueChange = onFirstValueChange,
                onDone = onDone,
                label = firstValueLabel,
                Unit = firstValueUnit
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            CustomOutlinedNumberTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                value = secondValue,
                onValueChange = onSecondValueChange,
                onDone = onDone,
                label = secondValueLabel,
                Unit = secondValueUnit
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        val visualTransformation = remember {
            ThousandSeparatorVisualTransformationWithAddedUnit(
                addedUnit = "TL"
            )
        }
        val transformedText = remember(resultText) {
            visualTransformation.filter(AnnotatedString(resultText))
        }.text
        Row(
            modifier = Modifier
                .height(spacing.spaceExtraLarge)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = MaterialTheme.shapes.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = transformedText,
                style = if(transformedText.length < 16){
                    MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                }else{
                    MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                },
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}