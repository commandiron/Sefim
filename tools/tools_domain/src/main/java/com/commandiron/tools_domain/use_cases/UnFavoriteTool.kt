package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository

class UnFavoriteTool(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(tool: Tool) =
        repository.insertTool(tool.copy(isFavorite = false))
}