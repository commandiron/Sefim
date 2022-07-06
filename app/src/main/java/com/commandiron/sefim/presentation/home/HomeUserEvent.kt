package com.commandiron.sefim.presentation.home

import com.commandiron.core.model.News
import com.commandiron.core.model.Tool

sealed class HomeUserEvent{
    object ProfilePictureClick: HomeUserEvent()
    object EditClick: HomeUserEvent()
    object NotificationClick: HomeUserEvent()
    object AddClick: HomeUserEvent()
    data class IconClick(val tool: Tool): HomeUserEvent()
    object ToolLongClick: HomeUserEvent()
    object SpaceClick: HomeUserEvent()
    data class FavoriteClick(val tool: Tool): HomeUserEvent()
    data class UnFavoriteClick(val tool: Tool): HomeUserEvent()

    object NewsRefresh: HomeUserEvent()

    object RebarPriceClick: HomeUserEvent()
    data class NewToolClick(val tool: Tool): HomeUserEvent()
    data class NewsClick(val news: News): HomeUserEvent()
}
