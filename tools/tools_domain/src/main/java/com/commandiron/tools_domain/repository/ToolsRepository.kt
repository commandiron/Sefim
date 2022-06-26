package com.commandiron.tools_domain.repository

import com.commandiron.tools_domain.model.ToolPresentation
import kotlinx.coroutines.flow.Flow

interface ToolsRepository {
    suspend fun insertAllTools(tools: List<ToolPresentation>)
    fun getAllTools(): Flow<List<ToolPresentation>>
    suspend fun insertTool(toolPresentation: ToolPresentation)
}