package com.commandiron.core.util

sealed class UiEvent {
    data class NavigateTo(val route: String): UiEvent()
}