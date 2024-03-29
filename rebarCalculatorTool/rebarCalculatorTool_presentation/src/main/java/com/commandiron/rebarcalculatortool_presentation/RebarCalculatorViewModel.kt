package com.commandiron.rebarcalculatortool_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.rebarcalculatortool_domain.use_cases.RebarCalculatorToolUseCases
import com.commandiron.rebarcalculatortool_presentation.model.RebarCalculatorItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.math.roundToLong


@HiltViewModel
class RebarCalculatorViewModel @Inject constructor(
    private val rebarCalculatorUseCases: RebarCalculatorToolUseCases,
): ViewModel() {

    var state by mutableStateOf(RebarCalculatorToolState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(userEvent:  RebarCalculatorUserEvent) {
        when (userEvent) {
            RebarCalculatorUserEvent.Back -> {
                sendUiEvent(UiEvent.NavigateUp)
            }
            RebarCalculatorUserEvent.AddRebarCalculatorItem -> {
                state = state.copy(
                    rebarCalculatorItems = state.rebarCalculatorItems.plus(RebarCalculatorItem())
                )

            }
            is RebarCalculatorUserEvent.DeleteRebarCalculatorItem -> {
                state = state.copy(
                    rebarCalculatorItems =
                    state.rebarCalculatorItems
                        .toMutableList()
                        .also {
                            it.removeAt(userEvent.index)
                        }
                )
                calculateGrandResult()
            }
            is RebarCalculatorUserEvent.PieceValueChange -> {
                state = state.copy(
                    rebarCalculatorItems =
                        state.rebarCalculatorItems
                            .toMutableList()
                            .also {
                                it.set(
                                    index = userEvent.index,
                                    element = state
                                        .rebarCalculatorItems[userEvent.index]
                                        .copy(pieceValue = userEvent.text)
                                )
                            }
                )
                calculateRebarWeight(userEvent.index)
            }
            is RebarCalculatorUserEvent.LengthValueChange -> {
                state = state.copy(
                    rebarCalculatorItems =
                    state.rebarCalculatorItems
                        .toMutableList()
                        .also {
                            it.set(
                                index = userEvent.index,
                                element = state
                                    .rebarCalculatorItems[userEvent.index]
                                    .copy(lengthValue = userEvent.text)
                            )
                        }
                )
                calculateRebarWeight(userEvent.index)
            }
            is RebarCalculatorUserEvent.DropDownClick -> {
                state = state.copy(
                    rebarCalculatorItems =
                    state.rebarCalculatorItems
                        .toMutableList()
                        .also {
                            it.set(
                                index = userEvent.index,
                                element = state
                                    .rebarCalculatorItems[userEvent.index]
                                    .copy(dropDownIsExpanded = true)
                            )
                        }
                )
                calculateRebarWeight(userEvent.index)
            }
            is RebarCalculatorUserEvent.DropDownDismissClick -> {
                state = state.copy(
                    rebarCalculatorItems =
                    state.rebarCalculatorItems
                        .toMutableList()
                        .also {
                            it.set(
                                index = userEvent.index,
                                element = state
                                    .rebarCalculatorItems[userEvent.index]
                                    .copy(dropDownIsExpanded = false)
                            )
                        }
                )
            }
            is RebarCalculatorUserEvent.DropDownSelect -> {
                state = state.copy(
                    rebarCalculatorItems =
                    state.rebarCalculatorItems
                        .toMutableList()
                        .also {
                            it.set(
                                index = userEvent.index,
                                element = state
                                    .rebarCalculatorItems[userEvent.index]
                                    .copy(
                                        diameterValue = userEvent.text,
                                        dropDownIsExpanded = false
                                    )
                            )
                        }
                )
                calculateRebarWeight(userEvent.index)
            }
        }
    }

    private fun calculateRebarWeight(index: Int) {
        if(state.rebarCalculatorItems[index].pieceValue.isNotEmpty() &&
            state.rebarCalculatorItems[index].lengthValue.isNotEmpty() &&
            state.rebarCalculatorItems[index].diameterValue.isNotEmpty()
        ){
            val result = rebarCalculatorUseCases.calculateRebarWeight(
                piece = state.rebarCalculatorItems[index].pieceValue.toInt(),
                length = state.rebarCalculatorItems[index].lengthValue.toDouble(),
                diameter = state.rebarCalculatorItems[index].diameterValue.filter { it.isDigit() }.toDouble(),
            )
            state = state.copy(
                rebarCalculatorItems =
                state.rebarCalculatorItems
                    .toMutableList()
                    .also {
                        it.set(
                            index = index,
                            element = state
                                .rebarCalculatorItems[index]
                                .copy(
                                    resultText = result.toBigDecimal().toPlainString()
                                )
                        )
                    }
            )
            calculateGrandResult()
        }else{
            state = state.copy(
                rebarCalculatorItems =
                state.rebarCalculatorItems
                    .toMutableList()
                    .also {
                        it.set(
                            index = index,
                            element = state
                                .rebarCalculatorItems[index]
                                .copy(
                                    resultText = "--"
                                )
                        )
                    }
            )
        }
    }

    private fun calculateGrandResult(){
        var grandResult = 0.0
        state.rebarCalculatorItems.forEach {
            if(it.resultText.toDoubleOrNull() != null){
                grandResult += it.resultText.toDouble()
            }
        }
        grandResult = (grandResult * 1000.0).roundToLong() / 1000.0
        state = state.copy(
            grandResult = grandResult.toBigDecimal().toPlainString(),
            grandResult2 = (((grandResult / 1000) * 100.0).roundToLong() / 100.0).toBigDecimal().toPlainString()
        )
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}















