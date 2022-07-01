package com.commandiron.sefim.presentation.home

import com.commandiron.news_domain.model.News
import com.commandiron.tools_domain.model.Tool

sealed class HomeUserEvent{
    object AddClick: HomeUserEvent()
    data class IconClick(val tool: Tool): HomeUserEvent()
    object ToolLongClick: HomeUserEvent()
    object SpaceClick: HomeUserEvent()
    data class FavoriteClick(val tool: Tool): HomeUserEvent()
    data class UnFavoriteClick(val tool: Tool): HomeUserEvent()

    object RebarPriceClick: HomeUserEvent()
    data class NewToolClick(val tool: Tool): HomeUserEvent()
    data class NewsClick(val news: News): HomeUserEvent()
}
