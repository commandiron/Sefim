package com.commandiron.tools_presentation.tools

import com.commandiron.core.model.Tool

sealed class ToolsUserEvent{
    data class IconClick(val tool: Tool): ToolsUserEvent()
    data class Favorite(val tool: Tool): ToolsUserEvent()
    data class SearchChange(val text: String): ToolsUserEvent()
}
