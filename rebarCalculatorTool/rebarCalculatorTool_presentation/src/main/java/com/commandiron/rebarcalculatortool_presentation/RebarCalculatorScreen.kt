package com.commandiron.rebarcalculatortool_presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.components.*
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings.Turkish.REBAR_QUANTITY_CALCULATOR
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.WindowInfo
import com.commandiron.rebarcalculatortool_presentation.components.RebarCalculatorAddRowButton
import com.commandiron.rebarcalculatortool_presentation.components.RebarCalculatorResultRow

@Composable
fun RebarCalculatorScreen(
    viewModel: RebarCalculatorViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val spacing = LocalSpacing.current
    val windowTypeInfo = LocalWindowTypeInfo.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
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
                if (windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    spacing.defaultScreenPaddingForCompact
                } else spacing.defaultScreenPaddingForExpandedNoBottomNav
            )
    ) {
        ToolHeader(
            title = REBAR_QUANTITY_CALCULATOR,
            onBackClick = {viewModel.onEvent(RebarCalculatorUserEvent.Back)},
            onFavoriteClick = { TODO() }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
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
                                onNext = {},
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
                                onNext = {},
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
                                        color = MaterialTheme.colorScheme.tertiaryContainer,
                                        shape = MaterialTheme.shapes.large
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val transformedText = getThousandSeparatorTransformedText(text = item.resultText, addedUnit = item.resultUnit)
                                Text(
                                    text = transformedText,
                                    style = if(transformedText.length <= 12 )
                                        MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) else
                                        MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        }
                    }
                )
            }
            item {
                RebarCalculatorResultRow(viewModel = viewModel)
            }
            item {
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                RebarCalculatorAddRowButton(viewModel = viewModel)
                Spacer(modifier = Modifier.height(spacing.spaceXXLarge))
            }
        }
    }
}