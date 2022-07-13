package com.commandiron.aeratedconctool_presentation.components.converters

import androidx.compose.runtime.Composable
import com.commandiron.aeratedconctool_presentation.AeratedConcToolState
import com.commandiron.aeratedconctool_presentation.AeratedConcToolUserEvent
import com.commandiron.aeratedconctool_presentation.AeratedConcToolViewModel
import com.commandiron.aeratedconctool_presentation.components.AeratedConcConverterComponent

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