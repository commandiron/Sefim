package com.commandiron.tools_presentation.aeratedConcTool

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.Strings.CUBIC_METER
import com.commandiron.core_ui.util.Strings.EMPTY_RESULT_DOUBLE_DASH
import com.commandiron.core_ui.util.Strings.EMPTY_STRING
import com.commandiron.core_ui.util.Strings.SQUARE_METER
import com.commandiron.core_ui.util.Strings.Turkish.PALLET
import com.commandiron.core_ui.util.Strings.Turkish.PIECE
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AeratedConcToolViewModel @Inject constructor(
    private val toolsUseCases: ToolsUseCases,
): ViewModel() {

    var state by mutableStateOf(AeratedConcToolState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(userEvent: AeratedConcToolUserEvent) {
        when (userEvent) {
            is AeratedConcToolUserEvent.SquareMetersPalletChange -> {
                state = state.copy(squareMetersPallet = userEvent.text)
                if(state.squareMetersPalletUnitConverterIsChanged){
                    calculatePalletToSquareMeter()
                }else{
                    calculateSquareMeterToPallet()
                }
            }
            AeratedConcToolUserEvent.SquareMetersPalletChangeUnitClick -> {
                state = state.copy(
                    squareMetersPalletUnitConverterIsChanged = !state.squareMetersPalletUnitConverterIsChanged,
                    squareMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
                )
                if(state.squareMetersPalletUnitConverterIsChanged){
                    state = state.copy(
                        squareMetersPallet = EMPTY_STRING,
                        squareMetersPalletLabel = PALLET,
                        squareMetersPalletUnit = PALLET,
                        squareMetersPalletResultUnit = SQUARE_METER,
                    )
                }else{
                    state = state.copy(
                        squareMetersPallet = EMPTY_STRING,
                        squareMetersPalletLabel = SQUARE_METER,
                        squareMetersPalletUnit = SQUARE_METER,
                        squareMetersPalletResultUnit = PALLET,
                    )
                }
            }
            AeratedConcToolUserEvent.SquareMetersPiecePalletThicknessClick -> {
                state = state.copy(
                    squareMetersPalletThicknessDropDownIsExpanded =
                    !state.squareMetersPalletThicknessDropDownIsExpanded
                )
            }
            is AeratedConcToolUserEvent.SquareMetersPalletThicknessDropDownSelect -> {
                state = state.copy(
                    thickness = userEvent.text,
                    squareMetersPalletThicknessDropDownIsExpanded = false
                )
                if(state.squareMetersPalletUnitConverterIsChanged){
                    calculatePalletToSquareMeter()
                }else{
                    calculateSquareMeterToPallet()
                }
            }

            is AeratedConcToolUserEvent.CubicMetersPalletChange -> {
                state = state.copy(cubicMetersPallet = userEvent.text)
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    calculatePalletToCubicMeter()
                }else{
                    calculateCubicMeterToPallet()
                }
            }
            AeratedConcToolUserEvent.CubicMetersPalletChangeUnitClick -> {
                state = state.copy(
                    cubicMetersPalletUnitConverterIsChanged = !state.cubicMetersPalletUnitConverterIsChanged,
                    cubicMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
                )
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    state = state.copy(
                        cubicMetersPallet = EMPTY_STRING,
                        cubicMetersPalletLabel = PALLET,
                        cubicMetersPalletUnit = PALLET,
                        cubicMetersPalletResultUnit = CUBIC_METER,
                    )
                }else{
                    state = state.copy(
                        cubicMetersPallet = EMPTY_STRING,
                        cubicMetersPalletLabel = CUBIC_METER,
                        cubicMetersPalletUnit = CUBIC_METER,
                        cubicMetersPalletResultUnit = PALLET,
                    )
                }
            }
            AeratedConcToolUserEvent.CubicMetersPiecePalletThicknessClick -> {
                state = state.copy(
                    cubicMetersPalletThicknessDropDownIsExpanded =
                    !state.cubicMetersPalletThicknessDropDownIsExpanded
                )
            }
            is AeratedConcToolUserEvent.CubicMetersPalletThicknessDropDownSelect -> {
                state = state.copy(
                    thickness = userEvent.text,
                    cubicMetersPalletThicknessDropDownIsExpanded = false
                )
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    calculatePalletToCubicMeter()
                }else{
                    calculateCubicMeterToPallet()
                }
            }

            is AeratedConcToolUserEvent.PiecePalletChange -> {
                state = state.copy(piecePallet = userEvent.text)
                if(state.piecePalletUnitConverterIsChanged){
                    calculatePalletToUnit()
                }else{
                    calculateUnitToPallet()
                }
            }
            AeratedConcToolUserEvent.PiecePalletChangeUnitClick -> {
                state = state.copy(
                    piecePalletUnitConverterIsChanged = !state.piecePalletUnitConverterIsChanged,
                    piecePalletResult = EMPTY_RESULT_DOUBLE_DASH
                )
                if(state.piecePalletUnitConverterIsChanged){
                    state = state.copy(
                        piecePallet = EMPTY_STRING,
                        piecePalletLabel = PALLET,
                        piecePalletUnit = PALLET,
                        piecePalletResultUnit = PIECE,
                    )
                }else{
                    state = state.copy(
                        piecePallet = EMPTY_STRING,
                        piecePalletLabel = PIECE,
                        piecePalletUnit = PIECE,
                        piecePalletResultUnit = PALLET,
                    )
                }
            }
            AeratedConcToolUserEvent.PiecePalletThicknessClick -> {
                state = state.copy(
                    piecePalletThicknessDropDownIsExpanded =
                    !state.piecePalletThicknessDropDownIsExpanded
                )
            }
            is AeratedConcToolUserEvent.PiecePalletThicknessDropDownSelect -> {
                state = state.copy(
                    thickness = userEvent.text,
                    piecePalletThicknessDropDownIsExpanded = false
                )
                if(state.piecePalletUnitConverterIsChanged){
                    calculatePalletToUnit()
                }else{
                    calculateUnitToPallet()
                }
            }

            is AeratedConcToolUserEvent.SquareCubicChange -> {
                state = state.copy(squareCubic = userEvent.text)
                if(state.squareCubicUnitConverterIsChanged){
                    calculateCubicToSquare()
                }else{
                    calculateSquareToCubic()
                }
            }
            AeratedConcToolUserEvent.SquareCubicChangeUnitClick -> {
                state = state.copy(
                    squareCubicUnitConverterIsChanged = !state.squareCubicUnitConverterIsChanged,
                    squareCubicResult = EMPTY_RESULT_DOUBLE_DASH
                )
                if(state.squareCubicUnitConverterIsChanged){
                    state = state.copy(
                        squareCubic = EMPTY_STRING,
                        squareCubicLabel = CUBIC_METER,
                        squareCubicUnit = CUBIC_METER,
                        squareCubicResultUnit = SQUARE_METER,
                    )
                }else{
                    state = state.copy(
                        squareCubic = EMPTY_STRING,
                        squareCubicLabel = SQUARE_METER,
                        squareCubicUnit = SQUARE_METER,
                        squareCubicResultUnit = CUBIC_METER,
                    )
                }
            }
            AeratedConcToolUserEvent.SquareCubicThicknessClick -> {
                state = state.copy(
                    squareCubicThicknessDropDownIsExpanded =
                    !state.squareCubicThicknessDropDownIsExpanded
                )
            }
            is AeratedConcToolUserEvent.SquareCubicThicknessDropDownSelect -> {
                state = state.copy(
                    thickness = userEvent.text,
                    squareCubicThicknessDropDownIsExpanded = false
                )
                if(state.squareCubicUnitConverterIsChanged){
                    calculateCubicToSquare()
                }else{
                    calculateSquareToCubic()
                }
            }

            AeratedConcToolUserEvent.KeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            AeratedConcToolUserEvent.ThicknessDropDownDismissClick -> {
                state = state.copy(
                    squareMetersPalletThicknessDropDownIsExpanded = false,
                    cubicMetersPalletThicknessDropDownIsExpanded = false,
                    piecePalletThicknessDropDownIsExpanded = false
                )
            }
            AeratedConcToolUserEvent.Back -> {
                sendUiEvent(UiEvent.NavigateUp)
            }
        }
    }

    private fun calculateUnitToPallet(){
        if(state.piecePallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculatePieceToPallet(
                piece = state.piecePallet.toInt(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                piecePalletResult = result.toString()
            )
        }else{
            state = state.copy(
                piecePalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculatePalletToUnit(){
        if(state.piecePallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculatePalletToPiece(
                pallet = state.piecePallet.toInt(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                piecePalletResult = result.toString()
            )
        }else{
            state = state.copy(
                piecePalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculateSquareMeterToPallet(){
        if(state.squareMetersPallet.isNotEmpty() &&
                state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculateSquareMeterToPallet(
                squareMeter = state.squareMetersPallet.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                squareMetersPalletResult = result.toString()
            )
        }else{
            state = state.copy(
                squareMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculatePalletToSquareMeter(){
        if(state.squareMetersPallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculatePalletToSquareMeter(
                pallet = state.squareMetersPallet.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                squareMetersPalletResult = result.toString()
            )
        }else{
            state = state.copy(
                squareMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculateCubicMeterToPallet(){
        if(state.cubicMetersPallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculateCubicMeterToPallet(
                cubicMeter = state.cubicMetersPallet.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                cubicMetersPalletResult = result.toString()
            )
        }else{
            state = state.copy(
                cubicMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculatePalletToCubicMeter(){
        if(state.cubicMetersPallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculatePalletToCubicMeter(
                pallet = state.cubicMetersPallet.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                cubicMetersPalletResult = result.toString()
            )
        }else{
            state = state.copy(
                cubicMetersPalletResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculateSquareToCubic(){
        if(state.squareCubic.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculateSquareToCubic(
                squareMeter = state.squareCubic.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                squareCubicResult = result.toString()
            )
        }else{
            state = state.copy(
                squareCubicResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun calculateCubicToSquare(){
        if(state.squareCubic.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculateCubicToSquare(
                cubicMeter = state.squareCubic.toDouble(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                squareCubicResult = result.toString()
            )
        }else{
            state = state.copy(
                squareCubicResult = EMPTY_RESULT_DOUBLE_DASH
            )
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}