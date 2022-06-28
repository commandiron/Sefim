package com.commandiron.sefim.presentation.home

import com.commandiron.news_domain.model.NewsContentPresentation
import com.commandiron.tools_domain.model.Tool

data class HomeState(
    val profileImageUrl: String? = null,
    val favoriteTools: List<Tool>? = null,
    val recommendedTools: List<Tool>? = null,
    val newsContent: NewsContentPresentation? = null,
    val isFavoriteIconsWobbling: Boolean = false
)
