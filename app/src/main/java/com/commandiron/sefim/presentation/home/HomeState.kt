package com.commandiron.sefim.presentation.home

import com.commandiron.news_presentation.model.NewsContentPresentation
import com.commandiron.news_presentation.model.defaultNewsContent
import com.commandiron.tools_presentation.model.ToolPresentation
import com.commandiron.tools_presentation.model.defaultFavoriteTools
import com.commandiron.tools_presentation.model.defaultTools

data class HomeState(
    val favoriteTools: List<ToolPresentation> = defaultFavoriteTools,
    val recommendedTools: List<ToolPresentation> = defaultTools,
    val newsContent: NewsContentPresentation = defaultNewsContent,
    val isFavoriteIconsWobbling: Boolean = false
)
