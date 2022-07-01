package com.commandiron.tools_presentation.aeratedConcTool.components.converters

import androidx.compose.runtime.Composable
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolState
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolUserEvent
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolViewModel
import com.commandiron.tools_presentation.aeratedConcTool.components.common.AeratedConcConverterComponent

@Composable
fun SquareCubicConverter(
    state: AeratedConcToolState,
    viewModel: AeratedConcToolViewModel
) {
    AeratedConcConverterComponent(
        title = state.squareCubicLabel
                + " - "
                + state.squareCubicResultUnit,
        onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.SquareCubicChangeUnitClick) },
        firstValue = state.squareCubic,
        firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.SquareCubicChange(it)) },
        firstValueLabel = state.squareCubicLabel,
        firstValueUnit = state.squareCubicUnit,
        firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.KeyboardDone) },
        secondValue = state.thickness,
        secondValueChange = {},
        secondValueLabel = state.thicknessLabel,
        secondValueUnit = state.thicknessUnit,
        secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.SquareCubicThicknessClick) },
        dropDownIsExpanded = state.squareCubicThicknessDropDownIsExpanded,
        dropDownItems = state.thicknessList,
        onDropDownItemSelect = {
            viewModel.onEvent(AeratedConcToolUserEvent.SquareCubicThicknessDropDownSelect(it))
        },
        onDropDownDismissRequest = {
            viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
        },
        resultText = state.squareCubicResult,
        resultUnit = state.squareCubicResultUnit
    )
}