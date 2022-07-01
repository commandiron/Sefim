package com.commandiron.tools_presentation.aeratedConcTool.components.converters

import androidx.compose.runtime.Composable
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolState
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolUserEvent
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolViewModel
import com.commandiron.tools_presentation.aeratedConcTool.components.AeratedConcConverterComponent

@Composable
fun PiecePalletConverter(
    state: AeratedConcToolState,
    viewModel: AeratedConcToolViewModel
) {
    AeratedConcConverterComponent(
        title = state.piecePalletLabel
                + " - "
                + state.piecePalletResultUnit,
        onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.PiecePalletChangeUnitClick) },
        firstValue = state.piecePallet,
        firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.PiecePalletChange(it)) },
        firstValueLabel = state.piecePalletLabel,
        firstValueUnit = state.piecePalletUnit,
        firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.KeyboardDone) },
        secondValue = state.thickness,
        secondValueChange = {},
        secondValueLabel = state.thicknessLabel,
        secondValueUnit = state.thicknessUnit,
        secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.PiecePalletThicknessClick) },
        dropDownIsExpanded = state.piecePalletThicknessDropDownIsExpanded,
        dropDownItems = state.thicknessList,
        onDropDownItemSelect = {
            viewModel.onEvent(AeratedConcToolUserEvent.PiecePalletThicknessDropDownSelect(it))
        },
        onDropDownDismissRequest = {
            viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
        },
        resultText = state.piecePalletResult,
        resultUnit = state.piecePalletResultUnit
    )
}