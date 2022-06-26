package com.commandiron.tools_presentation.tools

import com.commandiron.tools_domain.model.ToolPresentation

data class ToolsState(
    val allTools: List<ToolPresentation>? = null
)
