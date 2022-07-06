package com.commandiron.aeratedconctool_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.commandiron.core_ui.R
import com.commandiron.core_ui.components.CustomOutlinedNumberTextField
import com.commandiron.core_ui.components.OutlinedDropDown
import com.commandiron.core_ui.util.LocalSpacing

@Composable
fun AeratedConcConverterComponent(
    modifier: Modifier = Modifier,
    title: String,
    onChangeUnitClick:() -> Unit,
    firstValue: String,
    firstValueChange:(String) -> Unit,
    firstValueLabel: String,
    firstValueUnit: String,
    firstValueOnDone: () -> Unit,
    secondValue: String,
    secondValueChange:(String) -> Unit,
    secondValueLabel: String,
    secondValueUnit: String,
    secondValueOnClick: () -> Unit,
    dropDownIsExpanded: Boolean,
    dropDownItems: List<String>,
    onDropDownItemSelect: (String) -> Unit,
    onDropDownDismissRequest: (Int?) -> Unit,
    resultText: String,
    resultUnit: String
) {
    val spacing = LocalSpacing.current
    Column(modifier = modifier) {
        Row(modifier = Modifier.height(spacing.spaceLarge)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            IconButton(
                modifier = Modifier.padding(spacing.spaceExtraSmall),
                onClick = onChangeUnitClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.change),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Row(
            modifier = Modifier.height(spacing.spaceExtraLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomOutlinedNumberTextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                value = firstValue,
                onValueChange = firstValueChange,
                onDone = firstValueOnDone,
                label = firstValueLabel,
                Unit = firstValueUnit
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            OutlinedDropDown(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                value = secondValue,
                onValueChange = secondValueChange,
                label = secondValueLabel,
                Unit = secondValueUnit,
                onClick = secondValueOnClick,
                isExpanded = dropDownIsExpanded,
                items = dropDownItems,
                onItemSelect = onDropDownItemSelect,
                onDismissRequest = onDropDownDismissRequest
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.large
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = resultText,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                if(resultText.isNotEmpty()){
                    Text(
                        text = resultUnit,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}