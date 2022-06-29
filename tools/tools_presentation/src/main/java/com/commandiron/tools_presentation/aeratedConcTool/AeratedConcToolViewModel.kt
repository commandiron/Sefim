package com.commandiron.tools_presentation.aeratedConcTool

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.UiEvent
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
                    //Calculate
                }else{
                    //Calculate
                }
            }
            AeratedConcToolUserEvent.SquareMetersPalletKeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            AeratedConcToolUserEvent.SquareMetersPalletChangeUnitClick -> {
                state = state.copy(
                    squareMetersPalletUnitConverterIsChanged = !state.squareMetersPalletUnitConverterIsChanged,
                    squareMetersPalletResult = "--"
                )
                if(state.squareMetersPalletUnitConverterIsChanged){
                    state = state.copy(
                        squareMetersPallet = "",
                        squareMetersPalletLabel = "Palet",
                        squareMetersPalletSymbol = " Palet",
                        squareMetersPalletResultSymbol = "m2",
                    )
                }else{
                    state = state.copy(
                        squareMetersPallet = "",
                        squareMetersPalletLabel = "m2",
                        squareMetersPalletSymbol = " m2",
                        squareMetersPalletResultSymbol = "Palet",
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
                    thickness = state
                        .thicknessList[userEvent.listIndex],
                    squareMetersPalletThicknessDropDownIsExpanded = false
                )
                if(state.squareMetersPalletUnitConverterIsChanged){
                    //Calculate
                }else{
                    //Calculate
                }
            }

            is AeratedConcToolUserEvent.CubicMetersPalletChange -> {
                state = state.copy(cubicMetersPallet = userEvent.text)
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    //Calculate
                }else{
                    //Calculate
                }
            }
            AeratedConcToolUserEvent.CubicMetersPalletKeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            AeratedConcToolUserEvent.CubicMetersPalletChangeUnitClick -> {
                state = state.copy(
                    cubicMetersPalletUnitConverterIsChanged = !state.cubicMetersPalletUnitConverterIsChanged,
                    cubicMetersPalletResult = "--"
                )
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    state = state.copy(
                        cubicMetersPallet = "",
                        cubicMetersPalletLabel = "Palet",
                        cubicMetersPalletSymbol = " Palet",
                        cubicMetersPalletResultSymbol = "m3",
                    )
                }else{
                    state = state.copy(
                        cubicMetersPallet = "",
                        cubicMetersPalletLabel = "m3",
                        cubicMetersPalletSymbol = " m3",
                        cubicMetersPalletResultSymbol = "Palet",
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
                    thickness = state
                        .thicknessList[userEvent.listIndex],
                    cubicMetersPalletThicknessDropDownIsExpanded = false
                )
                if(state.cubicMetersPalletUnitConverterIsChanged){
                    //Calculate
                }else{
                    //Calculate
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
            AeratedConcToolUserEvent.PiecePalletKeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            AeratedConcToolUserEvent.PiecePalletChangeUnitClick -> {
                state = state.copy(
                    piecePalletUnitConverterIsChanged = !state.piecePalletUnitConverterIsChanged,
                    piecePalletResult = "--"
                )
                if(state.piecePalletUnitConverterIsChanged){
                    state = state.copy(
                        piecePallet = "",
                        piecePalletLabel = "Palet",
                        piecePalletSymbol = " Palet",
                        piecePalletResultSymbol = "Adet",
                    )
                }else{
                    state = state.copy(
                        piecePallet = "",
                        piecePalletLabel = "Adet",
                        piecePalletSymbol = " Adet",
                        piecePalletResultSymbol = "Palet",
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
                    thickness = state
                        .thicknessList[userEvent.listIndex],
                    piecePalletThicknessDropDownIsExpanded = false
                )
                if(state.piecePalletUnitConverterIsChanged){
                    calculatePalletToUnit()
                }else{
                    calculateUnitToPallet()
                }
            }

            AeratedConcToolUserEvent.ThicknessDropDownDismissClick -> {
                state = state.copy(
                    squareMetersPalletThicknessDropDownIsExpanded = false,
                    cubicMetersPalletThicknessDropDownIsExpanded = false,
                    piecePalletThicknessDropDownIsExpanded = false
                )
            }
        }
    }

    private fun calculateUnitToPallet(){
        if(state.piecePallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculateUnitToPallet(
                unit = state.piecePallet.toInt(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                piecePalletResult = result.toString()
            )
        }
    }

    private fun calculatePalletToUnit(){
        if(state.piecePallet.isNotEmpty() &&
            state.thickness.isNotEmpty()){
            val result = toolsUseCases.calculatePalletToUnit(
                pallet = state.piecePallet.toInt(),
                thickness = state.thickness.toDouble()
            )
            state = state.copy(
                piecePalletResult = result.toString()
            )
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}