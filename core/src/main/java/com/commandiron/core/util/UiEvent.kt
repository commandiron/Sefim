package com.commandiron.core.util

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}