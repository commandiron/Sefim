package com.commandiron.sefim.presentation.hot_splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.domain.preferences.Preferences
import com.commandiron.news_domain.use_cases.NewsUseCases
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  HotSplashViewModel @Inject constructor(
    preferences: Preferences,
    newsUseCases: NewsUseCases,
    toolsUseCases: ToolsUseCases
): ViewModel() {
    init {
        preferences.saveShouldShowHotSplash(false)
        viewModelScope.launch {
            newsUseCases.prepopulateAllNewsIntoNewsDb()
            toolsUseCases.prePopulateAllToolsInToolsDb()
        }
    }
}