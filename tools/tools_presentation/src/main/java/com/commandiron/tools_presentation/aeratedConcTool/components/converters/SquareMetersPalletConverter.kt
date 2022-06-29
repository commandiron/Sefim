package com.commandiron.tools_presentation.aeratedConcTool.components.converters

import androidx.compose.runtime.Composable
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolState
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolUserEvent
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolViewModel
import com.commandiron.tools_presentation.aeratedConcTool.components.common.AeratedConcConverterComponent

@Composable
fun SquareMetersPalletConverter(
    state: AeratedConcToolState,
    viewModel: AeratedConcToolViewModel
) {
    AeratedConcConverterComponent(
        title = state.squareMetersPalletLabel
                + " - "
                + state.squareMetersPalletResultSymbol,
        onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.SquareMetersPalletChangeUnitClick) },
        firstValue = state.squareMetersPallet,
        firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.SquareMetersPalletChange(it)) },
        firstValueLabel = state.squareMetersPalletLabel,
        firstValueSymbol = state.squareMetersPalletSymbol,
        firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.SquareMetersPalletKeyboardDone) },
        secondValue = state.thickness,
        secondValueChange = {},
        secondValueLabel = state.thicknessLabel,
        secondValueSymbol = state.thicknessSymbol,
        secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.SquareMetersPiecePalletThicknessClick) },
        dropDownIsExpanded = state.squareMetersPalletThicknessDropDownIsExpanded,
        dropDownItems = state.thicknessList,
        onDropDownItemSelect = {
            viewModel.onEvent(AeratedConcToolUserEvent.SquareMetersPalletThicknessDropDownSelect(it))
        },
        onDropDownDismissRequest = {
            viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
        },
        resultText = state.squareMetersPalletResult,
        resultSymbol = state.squareMetersPalletResultSymbol
    )
}