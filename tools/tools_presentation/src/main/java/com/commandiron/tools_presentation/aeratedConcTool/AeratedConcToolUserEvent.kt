package com.commandiron.tools_presentation.aeratedConcTool

sealed class AeratedConcToolUserEvent{

    data class SquareMetersPalletChange(val text: String): AeratedConcToolUserEvent()
    object SquareMetersPalletChangeUnitClick: AeratedConcToolUserEvent()
    object SquareMetersPiecePalletThicknessClick: AeratedConcToolUserEvent()
    data class SquareMetersPalletThicknessDropDownSelect(val listIndex: Int): AeratedConcToolUserEvent()

    data class CubicMetersPalletChange(val text: String): AeratedConcToolUserEvent()
    object CubicMetersPalletChangeUnitClick: AeratedConcToolUserEvent()
    object CubicMetersPiecePalletThicknessClick: AeratedConcToolUserEvent()
    data class CubicMetersPalletThicknessDropDownSelect(val listIndex: Int): AeratedConcToolUserEvent()

    data class PiecePalletChange(val text: String): AeratedConcToolUserEvent()
    object PiecePalletChangeUnitClick: AeratedConcToolUserEvent()
    object PiecePalletThicknessClick: AeratedConcToolUserEvent()
    data class PiecePalletThicknessDropDownSelect(val listIndex: Int): AeratedConcToolUserEvent()

    data class SquareCubicChange(val text: String): AeratedConcToolUserEvent()
    object SquareCubicChangeUnitClick: AeratedConcToolUserEvent()
    object SquareCubicThicknessClick: AeratedConcToolUserEvent()
    data class SquareCubicThicknessDropDownSelect(val listIndex: Int): AeratedConcToolUserEvent()

    object KeyboardDone: AeratedConcToolUserEvent()
    object ThicknessDropDownDismissClick: AeratedConcToolUserEvent()
    object BackTextClick: AeratedConcToolUserEvent()
}
