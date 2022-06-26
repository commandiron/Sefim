package com.commandiron.sefim.presentation.home

sealed class HomeUserEvent{
    object SpaceClick: HomeUserEvent()
    object ToolLongClick: HomeUserEvent()
    object UnFavoriteClick: HomeUserEvent()
}
