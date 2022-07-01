package com.commandiron.sefim.presentation.home

import com.commandiron.sefim.presentation.home.model.HomeNews
import com.commandiron.tools_domain.model.Tool

data class HomeState(
    val profileImageUrl: String? = null,
    val favoriteTools: List<Tool>? = null,
    val recommendedTools: List<Tool>? = null,
    val homeNews: HomeNews? = null,
    val isFavoriteIconsWobbling: Boolean = false
)
