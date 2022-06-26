package com.commandiron.tools_domain.model

data class ToolPresentation(
    val id: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean = false,
    val toolTags: List<ToolTag>? = null
)