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
            is AeratedConcToolUserEvent.PieceChange -> {
                state = state.copy(piecePallet = userEvent.text)
                if(state.unitConverterIsChanged){
                    calculatePalletToUnit()
                }else{
                    calculateUnitToPallet()
                }
            }
            AeratedConcToolUserEvent.PieceKeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            AeratedConcToolUserEvent.ThicknessClick -> {
                state = state.copy(thicknessDropDownIsExpanded = !state.thicknessDropDownIsExpanded)
            }
            AeratedConcToolUserEvent.ThicknessDropDownDismissClick -> {
                state = state.copy(thicknessDropDownIsExpanded = false)
            }
            is AeratedConcToolUserEvent.ThicknessDropDownSelect -> {
                state = state.copy(
                    thickness = state
                        .thicknessList[userEvent.listIndex],
                    thicknessDropDownIsExpanded = false
                )
                if(state.unitConverterIsChanged){
                    calculatePalletToUnit()
                }else{
                    calculateUnitToPallet()
                }
            }
            AeratedConcToolUserEvent.ChangeUnitClick -> {
                state = state.copy(
                    unitConverterIsChanged = !state.unitConverterIsChanged,
                    result = ""
                )
                if(state.unitConverterIsChanged){
                    state = state.copy(
                        piecePallet = "",
                        piecePalletLabel = "Palet",
                        piecePalletSymbol = " Palet",
                        resultSymbol = "Adet",
                    )
                }else{
                    state = state.copy(
                        piecePallet = "",
                        piecePalletLabel = "Adet",
                        piecePalletSymbol = " Adet",
                        resultSymbol = "Palet",
                    )
                }
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
                result = result.toString()
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
                result = result.toString()
            )
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}