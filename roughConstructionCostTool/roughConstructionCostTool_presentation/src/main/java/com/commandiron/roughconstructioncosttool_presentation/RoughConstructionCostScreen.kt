package com.commandiron.roughconstructioncosttool_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.components.ToolHeader
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.Strings.CUBIC_METER
import com.commandiron.core_ui.util.Strings.SQUARE_METER
import com.commandiron.core_ui.util.Strings.TON
import com.commandiron.core_ui.util.Strings.TURKISH_LIRA
import com.commandiron.core_ui.util.Strings.Turkish.CONCRETE
import com.commandiron.core_ui.util.Strings.Turkish.FORMWORK
import com.commandiron.core_ui.util.Strings.Turkish.IRON
import com.commandiron.core_ui.util.Strings.Turkish.QUANTITY
import com.commandiron.core_ui.util.Strings.Turkish.QUANTITY_FLAT_MEASURE
import com.commandiron.core_ui.util.Strings.Turkish.ROUGH_CONSTRUCTION_COST_CALCULATOR
import com.commandiron.core_ui.util.Strings.Turkish.UNIT_PRICE
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.WindowInfo
import com.commandiron.roughconstructioncosttool_presentation.components.RoughConstructionCostBodyRow
import com.commandiron.roughconstructioncosttool_presentation.components.RoughConstructionCostFooter

@Composable
fun RoughConstructionCostScreen(
    viewModel: RoughConstructionCostViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    val localSoftwareKeyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.NavigateUp -> {
                    navigateUp()
                }
                UiEvent.HideKeyboard -> {
                    localSoftwareKeyboard?.hide()
                }
                else -> {}
            }
        }
    }
    if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        RoughConstructionCostCompactContent(viewModel)
    }else{
        RoughConstructionCostExpandedContent(viewModel)
    }
}

@Composable
fun RoughConstructionCostCompactContent(viewModel: RoughConstructionCostViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompactNoBottomNav)
    ) {
        item {
            ToolHeader(
                title = ROUGH_CONSTRUCTION_COST_CALCULATOR,
                onBackClick = { viewModel.onEvent(RoughConstructionCostUserEvent.Back) },
                onFavoriteClick = { TODO() }
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = FORMWORK,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.formWorkQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.FormWorkQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY_FLAT_MEASURE,
                firstValueUnit= SQUARE_METER,
                secondValue = state.formWorkUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.FormWorkUnitPriceTextChange(it))
                },
                onSecondNext = {},
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$SQUARE_METER",
                resultText = state.formWorkResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = IRON,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.rebarQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.RebarQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY,
                firstValueUnit= TON,
                secondValue = state.rebarUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.RebarUnitPriceTextChange(it))
                },
                onSecondNext = {},
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$TON",
                resultText = state.rebarResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = CONCRETE,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.concreteQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.ConcreteQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY,
                firstValueUnit= CUBIC_METER,
                secondValue = state.concreteUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.ConcreteUnitPriceTextChange(it))
                },
                onSecondNext = { viewModel.onEvent(RoughConstructionCostUserEvent.OnLastTextFieldNext) },
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$CUBIC_METER",
                resultText = state.concreteResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostFooter(resultText = state.grandTotalText)
        }
    }
}

@Composable
fun RoughConstructionCostExpandedContent(viewModel: RoughConstructionCostViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForExpandedNoBottomNav)
    ){
        item {
            ToolHeader(
                title = ROUGH_CONSTRUCTION_COST_CALCULATOR,
                onBackClick = { viewModel.onEvent(RoughConstructionCostUserEvent.Back) },
                onFavoriteClick = { TODO() }
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = FORMWORK,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.formWorkQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.FormWorkQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY_FLAT_MEASURE,
                firstValueUnit= SQUARE_METER,
                secondValue = state.formWorkUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.FormWorkUnitPriceTextChange(it))
                },
                onSecondNext = {},
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$SQUARE_METER",
                resultText = state.formWorkResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = IRON,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.rebarQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.RebarQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY,
                firstValueUnit= TON,
                secondValue = state.rebarUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.RebarUnitPriceTextChange(it))
                },
                onSecondNext = {},
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$TON",
                resultText = state.rebarResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = CONCRETE,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostBodyRow(
                firstValue = state.concreteQuantityText,
                onFirstValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.ConcreteQuantityTextChange(it))
                },
                onFirstNext = {},
                firstValueLabel= QUANTITY,
                firstValueUnit= CUBIC_METER,
                secondValue = state.concreteUnitPriceText,
                onSecondValueChange = {
                    viewModel.onEvent(RoughConstructionCostUserEvent.ConcreteUnitPriceTextChange(it))
                },
                onSecondNext = { viewModel.onEvent(RoughConstructionCostUserEvent.OnLastTextFieldNext) },
                secondValueLabel = UNIT_PRICE,
                secondValueUnit = "$TURKISH_LIRA/$CUBIC_METER",
                resultText = state.concreteResultText
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            RoughConstructionCostFooter(resultText = state.grandTotalText)
        }
    }
}