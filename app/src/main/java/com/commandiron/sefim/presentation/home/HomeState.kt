package com.commandiron.sefim.presentation.home

import com.commandiron.sefim.presentation.home.model.HomeNews
import com.commandiron.tools_domain.model.Tool

data class HomeState(
    val newsIsLoading: Boolean = false,
    val newsHasError: Boolean = false,
    val profileImageUrl: String? = null,
    val favoriteTools: List<Tool>? = null,
    val recommendedTools: List<Tool>? = null,
    val homeNews: HomeNews = HomeNews(),
    val isFavoriteIconsWobbling: Boolean = false
)
