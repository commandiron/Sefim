package com.commandiron.tools_presentation.rebarCalculatorTool

sealed class RebarCalculatorUserEvent{
    object BackTextClick: RebarCalculatorUserEvent()
    object AddRebarCalculatorItem: RebarCalculatorUserEvent()
    data class PieceValueChange(val index: Int, val text: String): RebarCalculatorUserEvent()
    data class LengthValueChange(val index: Int, val text: String): RebarCalculatorUserEvent()

    data class DropDownClick(val index: Int): RebarCalculatorUserEvent()
    data class DropDownDismissClick(val index: Int): RebarCalculatorUserEvent()
    data class DropDownSelect(val index: Int, val text: String): RebarCalculatorUserEvent()
}
