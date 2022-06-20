package com.commandiron

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppViewModel():ViewModel() {

    var state by mutableStateOf(AppState())
        private set

    init {
        viewModelScope.launch {
            delay(timeMillis = state.coldSplashScreenDelay)
            state = state.copy(
                isColdSplashScreenVisible = false
            )
        }
    }
}

data class AppState(
    val isColdSplashScreenVisible: Boolean = true,
    val coldSplashScreenDelay: Long = 1000L
)