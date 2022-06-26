package com.commandiron.sefim.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.UiEvent
import com.commandiron.sefim.navigation.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(userEvent: HomeUserEvent) {
        when (userEvent) {
            HomeUserEvent.ToolClick -> {
                if(!state.isFavoriteIconsWobbling){
                    sendUiEvent(UiEvent.NavigateTo(NavigationItem.Tool.route))
                }
            }
            HomeUserEvent.AddToolClick -> {
                sendUiEvent(UiEvent.NavigateTo(NavigationItem.Tools.route))
            }
            HomeUserEvent.ToolLongClick -> {
                state = state.copy(
                    isFavoriteIconsWobbling = true
                )
            }
            HomeUserEvent.SpaceClick -> {
                state = state.copy(
                    isFavoriteIconsWobbling = false
                )
            }
            HomeUserEvent.UnFavoriteClick -> {
                //Open Alert Dialog
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.send(uiEvent)
        }
    }
}