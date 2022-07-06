package com.commandiron.tools_domain.repository

import com.commandiron.core.model.Tool

interface ToolsRepository {
    suspend fun insertAllTools(tools: List<Tool>)
    suspend fun getAllTools(): List<Tool>
    suspend fun insertTool(tool: Tool)
}