package com.commandiron.tools_presentation.rebarCalculatorTool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.Strings.Turkish.GRAND_TOTAL
import com.commandiron.core_ui.Strings.Turkish.REBAR_QUANTITY_CALCULATOR
import com.commandiron.tools_presentation.components.CustomOutlinedNumberTextField
import com.commandiron.tools_presentation.components.OutlinedDropDown
import com.commandiron.tools_presentation.components.ToolHeader

@Composable
fun RebarCalculatorScreen(
    viewModel: RebarCalculatorViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.HideKeyboard -> {
                    keyboardController?.hide()
                }
                UiEvent.NavigateUp -> {
                    navigateUp()
                }
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPadding)
    ) {
        ToolHeader(
            title = REBAR_QUANTITY_CALCULATOR,
            onIconClick = {viewModel.onEvent(RebarCalculatorUserEvent.Back)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ){
            itemsIndexed(state.rebarCalculatorItems){ index, item ->
                Row(
                    modifier = Modifier.height(spacing.spaceExtraLarge),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomOutlinedNumberTextField(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        value = item.pieceValue,
                        onValueChange = {
                            viewModel.onEvent(
                                RebarCalculatorUserEvent.PieceValueChange(index, it)
                            )
                        },
                        onDone = {},
                        label = item.pieceLabel,
                        Unit = item.pieceUnit,
                        forceValueInt = true
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    CustomOutlinedNumberTextField(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        value = item.lengthValue,
                        onValueChange = {
                            viewModel.onEvent(
                                RebarCalculatorUserEvent.LengthValueChange(index, it)
                            )
                        },
                        onDone = {},
                        label = item.lengthLabel,
                        Unit = item.lengthUnit
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    OutlinedDropDown(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        value = item.diameterValue,
                        onValueChange = {},
                        label = item.diameterLabel,
                        Unit = item.diameterUnit,
                        onClick = { viewModel.onEvent(RebarCalculatorUserEvent.DropDownClick(index)) },
                        isExpanded = item.dropDownIsExpanded,
                        items = item.dropDownItems,
                        onItemSelect = { viewModel.onEvent(RebarCalculatorUserEvent.DropDownSelect(index, it)) },
                        onDismissRequest = { viewModel.onEvent(RebarCalculatorUserEvent.DropDownDismissClick(index)) }
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
                            text = item.resultText,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        if(item.resultText.isNotEmpty()){
                            Text(
                                text = item.resultUnit,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(spacing.spaceExtraLarge)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.large
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$GRAND_TOTAL : ",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = state.grandResult,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    if(state.grandResult.isNotEmpty()){
                        Text(
                            text = state.grandResultUnit,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                IconButton(
                    onClick = {
                        viewModel.onEvent(RebarCalculatorUserEvent.AddRebarCalculatorItem)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(spacing.spaceExtraLarge),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}