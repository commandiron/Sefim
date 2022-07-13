package com.commandiron.roughconstructioncosttool_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.commandiron.core_ui.components.CustomOutlinedNumberTextField
import com.commandiron.core_ui.components.getThousandSeparatorTransformedText
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings.TURKISH_LIRA

@Composable
fun RoughConstructionCostBodyRow(
    firstValue: String,
    onFirstValueChange:(String) -> Unit,
    onFirstNext:() -> Unit,
    firstValueLabel: String,
    firstValueUnit: String,
    secondValue: String,
    onSecondValueChange:(String) -> Unit,
    onSecondNext:() -> Unit,
    secondValueLabel: String,
    secondValueUnit: String,
    resultText: String
) {
    val spacing = LocalSpacing.current
    Column {
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
                onNext = onFirstNext,
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
                onNext = onSecondNext,
                label = secondValueLabel,
                Unit = secondValueUnit
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
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
            val transformedText = getThousandSeparatorTransformedText(text = resultText, addedUnit = TURKISH_LIRA)
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