package com.commandiron.tools_presentation.aeratedConcTool.components.converters

import androidx.compose.runtime.Composable
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolState
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolUserEvent
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolViewModel
import com.commandiron.tools_presentation.aeratedConcTool.components.AeratedConcConverterComponent

@Composable
fun CubicMetersPalletConverter(
    state: AeratedConcToolState,
    viewModel: AeratedConcToolViewModel
) {
    AeratedConcConverterComponent(
        title = state.cubicMetersPalletLabel
                + " - "
                + state.cubicMetersPalletResultUnit,
        onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.CubicMetersPalletChangeUnitClick) },
        firstValue = state.cubicMetersPallet,
        firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.CubicMetersPalletChange(it)) },
        firstValueLabel = state.cubicMetersPalletLabel,
        firstValueUnit = state.cubicMetersPalletUnit,
        firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.KeyboardDone) },
        secondValue = state.thickness,
        secondValueChange = {},
        secondValueLabel = state.thicknessLabel,
        secondValueUnit = state.thicknessUnit,
        secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.CubicMetersPiecePalletThicknessClick) },
        dropDownIsExpanded = state.cubicMetersPalletThicknessDropDownIsExpanded,
        dropDownItems = state.thicknessList,
        onDropDownItemSelect = {
            viewModel.onEvent(AeratedConcToolUserEvent.CubicMetersPalletThicknessDropDownSelect(it))
        },
        onDropDownDismissRequest = {
            viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
        },
        resultText = state.cubicMetersPalletResult,
        resultUnit = state.cubicMetersPalletResultUnit
    )
}