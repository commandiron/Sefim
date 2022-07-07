package com.commandiron.rebarcalculatortool_presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings.Turkish.GRAND_TOTAL
import com.commandiron.core_ui.util.Strings.Turkish.REBAR_QUANTITY_CALCULATOR
import com.commandiron.core_ui.components.CustomOutlinedNumberTextField
import com.commandiron.core_ui.components.OutlinedDropDown
import com.commandiron.core_ui.components.ToolHeader
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.WindowInfo

@Composable
fun RebarCalculatorScreen(
    viewModel: RebarCalculatorViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val spacing = LocalSpacing.current
    val windowTypeInfo = LocalWindowTypeInfo.current
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
            .padding(
                if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                    spacing.defaultScreenPaddingForCompact
                }else spacing.defaultScreenPaddingForExpandedNoBottomNav
            )
    ) {
        ToolHeader(
            title = REBAR_QUANTITY_CALCULATOR,
            onIconClick = {viewModel.onEvent(RebarCalculatorUserEvent.Back)}
        )
        Spacer(
            modifier = Modifier.height(
                if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
                    spacing.spaceLarge else spacing.spaceMedium
            )
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ){
            itemsIndexed(
                items = state.rebarCalculatorItems, key = { _, item ->
                    item.itemId
                }
            ){ index, item ->
                val dismissState = rememberDismissState()
                if(dismissState.isDismissed(DismissDirection.EndToStart)){
                    viewModel.onEvent(RebarCalculatorUserEvent.DeleteRebarCalculatorItem(index))
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.padding(vertical = 4.dp),
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.5f) },
                    background = {
                        dismissState.dismissDirection ?: return@SwipeToDismiss
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.Default -> Color.LightGray
                                DismissValue.DismissedToEnd -> Color.Green
                                DismissValue.DismissedToStart -> {
                                    Color.Red
                                }
                            }
                        )
                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier.scale(scale)
                            )
                        }
                    },
                    dismissContent = {
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
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                                if(item.resultText.isNotEmpty()){
                                    Text(
                                        text = item.resultUnit,
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        }
                    }
                )
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
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = state.grandResult,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    if(state.grandResult.isNotEmpty()){
                        Text(
                            text = state.grandResultUnit,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Text(
                        text = "=",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Text(
                        text = state.grandResult2,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    if(state.grandResult2.isNotEmpty()){
                        Text(
                            text = state.grandResult2Unit,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
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