package com.commandiron.rebarpricestool_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.rebarpricestool_domain.use_cases.RebarPricesToolUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RebarPricesViewModel @Inject constructor(
    private val rebarPricesToolUseCases: RebarPricesToolUseCases,
): ViewModel() {

    var state by mutableStateOf(RebarPricesToolState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getRebarPrices()
    }

    fun onEvent(userEvent:  RebarPricesUserEvent) {
        when (userEvent) {
            RebarPricesUserEvent.Back -> {
                sendUiEvent(UiEvent.NavigateUp)
            }
        }
    }

    private fun getRebarPrices(){
        viewModelScope.launch {
            rebarPricesToolUseCases.getRebarPrices().onEach { response ->
                when(response){
                    is Response.Error -> {
                    }
                    Response.Loading -> {
                    }
                    is Response.Success -> {
                        state = state.copy(
                            rebarPrices = response.data
                        )
                    }
                }
            }.collect()
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}