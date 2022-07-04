package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow

class GetAllTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() : Flow<Response<List<Tool>>> = repository.getAllTools()
}