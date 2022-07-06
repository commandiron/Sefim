package com.commandiron.sefim.presentation.home

import com.commandiron.core.model.News
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import com.commandiron.core.model.Tool

data class HomeState(
    val newsIsLoading: Boolean = false,
    val newsHasError: Boolean = false,
    val profileImageUrl: String? = null,
    val favoriteTools: List<Tool>? = null,
    val recommendedTools: List<Tool>? = null,
    val rebarPrice: RebarPrice? = null,
    val newTool: Tool? = null,
    val newsList: List<News>? = null,
    val isFavoriteIconsWobbling: Boolean = false
)
