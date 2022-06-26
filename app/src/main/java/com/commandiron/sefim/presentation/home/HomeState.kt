package com.commandiron.sefim.presentation.home

import com.commandiron.news_domain.model.NewsContentPresentation
import com.commandiron.tools_domain.model.ToolPresentation

data class HomeState(
    val profileImageUrl: String? = null,
    val favoriteTools: List<ToolPresentation>? = null,
    val recommendedTools: List<ToolPresentation>? = null,
    val newsContent: NewsContentPresentation? = null,
    val isFavoriteIconsWobbling: Boolean = false
)
