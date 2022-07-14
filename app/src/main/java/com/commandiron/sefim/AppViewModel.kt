package com.commandiron.sefim

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.news_domain.use_cases.NewsUseCases
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val toolsUseCases: ToolsUseCases
): ViewModel() {

    var state by mutableStateOf(AppState())
        private set

    init {
        viewModelScope.launch {
            println("1: " + toolsUseCases.checkDatabaseIsExist())
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