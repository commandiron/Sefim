package com.commandiron.sefim.presentation.home

sealed class HomeUserEvent{
    object SpaceClick: HomeUserEvent()
    object ToolClick: HomeUserEvent()
    object ToolLongClick: HomeUserEvent()
    object AddToolClick: HomeUserEvent()
    object UnFavoriteClick: HomeUserEvent()
}
