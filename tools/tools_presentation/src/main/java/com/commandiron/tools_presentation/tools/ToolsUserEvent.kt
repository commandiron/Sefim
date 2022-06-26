package com.commandiron.tools_presentation.tools

import com.commandiron.tools_domain.model.ToolPresentation

sealed class ToolsUserEvent{
    object ToolClick: ToolsUserEvent()
    data class Favorite(val toolPresentation: ToolPresentation): ToolsUserEvent()
}
