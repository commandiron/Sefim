package com.commandiron.tools_presentation.aeratedConcTool

sealed class AeratedConcToolUserEvent{

    data class PieceChange(val text: String): AeratedConcToolUserEvent()
    object PieceKeyboardDone: AeratedConcToolUserEvent()

    object ThicknessClick: AeratedConcToolUserEvent()
    object ThicknessDropDownDismissClick: AeratedConcToolUserEvent()
    data class ThicknessDropDownSelect(val listIndex: Int): AeratedConcToolUserEvent()

    object ChangeUnitClick: AeratedConcToolUserEvent()
}
