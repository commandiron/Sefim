package com.commandiron.rebarcalculatortool_presentation

sealed class RebarCalculatorUserEvent{
    object Back: RebarCalculatorUserEvent()
    object AddRebarCalculatorItem: RebarCalculatorUserEvent()
    data class DeleteRebarCalculatorItem(val index: Int): RebarCalculatorUserEvent()
    data class PieceValueChange(val index: Int, val text: String): RebarCalculatorUserEvent()
    data class LengthValueChange(val index: Int, val text: String): RebarCalculatorUserEvent()

    data class DropDownClick(val index: Int): RebarCalculatorUserEvent()
    data class DropDownDismissClick(val index: Int): RebarCalculatorUserEvent()
    data class DropDownSelect(val index: Int, val text: String): RebarCalculatorUserEvent()
}
