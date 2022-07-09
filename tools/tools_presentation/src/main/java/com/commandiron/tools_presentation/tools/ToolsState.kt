package com.commandiron.tools_presentation.tools

import com.commandiron.tools_domain.model.Tool

data class ToolsState(
    val allTools: List<Tool>? = null,
    val filteredTools: List<Tool>? = null,
    val searchText: String = "",
)
