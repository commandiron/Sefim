package com.commandiron.tools_domain.use_cases

import com.commandiron.core.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository

class GetAllTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() : List<Tool> = repository.getAllTools()
}