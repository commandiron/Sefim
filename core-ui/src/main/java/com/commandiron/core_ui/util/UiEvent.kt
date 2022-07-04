package com.commandiron.core_ui.util

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class Navigate(val route: String): UiEvent()
    object HideKeyboard: UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}