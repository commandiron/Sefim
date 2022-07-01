package com.commandiron.tools_domain.model

data class Tool(
    val id: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean = false,
    val toolTags: List<ToolTag> = listOf(),
    val route: String
)