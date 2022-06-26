package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.repository.ToolsRepository

class FavoriteTool(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(toolPresentation: ToolPresentation) =
        repository.insertTool(toolPresentation)
}