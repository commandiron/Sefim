package com.commandiron.tools_data.repository

import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toTool
import com.commandiron.core.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository

class ToolsRepositoryImpl(
    private val dao: ToolsDao,
): ToolsRepository {
    override suspend fun insertAllTools(tools: List<Tool>) {
        dao.insertAllTools(tools.map { it.toToolEntity() })
    }

    override suspend fun getAllTools(): List<Tool> {
        return dao.getAllTools().map { entities -> entities.toTool() }
    }

    override suspend fun insertTool(tool: Tool) {
        dao.insertTool(tool.toToolEntity())
    }
}