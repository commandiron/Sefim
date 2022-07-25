package com.commandiron.roughconstructioncosttool_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.roughconstructioncosttool_domain.use_cases.RoughConstructionCostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject


@HiltViewModel
class RoughConstructionCostViewModel @Inject constructor(
    private val roughConstructionCostUseCases: RoughConstructionCostUseCases,
): ViewModel() {

    var state by mutableStateOf(RoughConstructionCostState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getPrices()
    }

    fun onEvent(userEvent:  RoughConstructionCostUserEvent) {
        when (userEvent) {
            RoughConstructionCostUserEvent.Back -> {
                sendUiEvent(UiEvent.NavigateUp)
            }
            RoughConstructionCostUserEvent.OnLastTextFieldNext -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
            is RoughConstructionCostUserEvent.FormWorkQuantityTextChange -> {
                state = state.copy(formWorkQuantityText = userEvent.text)
                if(state.formWorkUnitPriceText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.formWorkQuantityText.toDoubleOrNull(),
                        secondValue = state.formWorkUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(formWorkResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(formWorkResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
            is RoughConstructionCostUserEvent.FormWorkUnitPriceTextChange -> {
                state = state.copy(formWorkUnitPriceText = userEvent.text)
                if(state.formWorkQuantityText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.formWorkQuantityText.toDoubleOrNull(),
                        secondValue = state.formWorkUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(formWorkResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(formWorkResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
            is RoughConstructionCostUserEvent.RebarQuantityTextChange -> {
                state = state.copy(rebarQuantityText = userEvent.text)
                if(state.rebarUnitPriceText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.rebarQuantityText.toDoubleOrNull(),
                        secondValue = state.rebarUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(rebarResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(rebarResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
            is RoughConstructionCostUserEvent.RebarUnitPriceTextChange -> {
                state = state.copy(rebarUnitPriceText = userEvent.text)
                if(state.rebarQuantityText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.rebarQuantityText.toDoubleOrNull(),
                        secondValue = state.rebarUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(rebarResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(rebarResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
            is RoughConstructionCostUserEvent.ConcreteQuantityTextChange -> {
                state = state.copy(concreteQuantityText = userEvent.text)
                if(state.concreteUnitPriceText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.concreteQuantityText.toDoubleOrNull(),
                        secondValue = state.concreteUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(concreteResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(concreteResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
            is RoughConstructionCostUserEvent.ConcreteUnitPriceTextChange -> {
                state = state.copy(concreteUnitPriceText = userEvent.text)
                if(state.concreteQuantityText.isNotEmpty()){
                    roughConstructionCostUseCases.multiplyTwoDoubleValue(
                        firstValue = state.concreteQuantityText.toDoubleOrNull(),
                        secondValue = state.concreteUnitPriceText.toDoubleOrNull()
                    )?.let {
                        state = state.copy(concreteResultText = it.toBigDecimal().toPlainString())
                    }?: kotlin.run {
                        state = state.copy(concreteResultText = "--")
                    }
                }
                calculateGrandTotal()
            }
        }
    }

    private fun getPrices(){
        state = state.copy(
            formWorkUnitPriceText = roughConstructionCostUseCases.getFormWorkPrice().toString(),
            rebarUnitPriceText = roughConstructionCostUseCases.getRebarPrice().toString(),
            concreteUnitPriceText = roughConstructionCostUseCases.getConcretePrice().toString()
        )
    }

    private fun calculateGrandTotal(){

        val formWorkResult = state.formWorkResultText.toDoubleOrNull() ?: 0.0
        val rebarResult = state.rebarResultText.toDoubleOrNull() ?: 0.0
        val concreteResult = state.concreteResultText.toDoubleOrNull() ?: 0.0

        state = state.copy(
            grandTotalText = (formWorkResult + rebarResult + concreteResult).toBigDecimal().toPlainString()
        )
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}