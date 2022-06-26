package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow

class GetAllTools(
    private val repository: ToolsRepository
) {
    operator fun invoke() : Flow<List<ToolPresentation>> = repository.getAllTools()
}