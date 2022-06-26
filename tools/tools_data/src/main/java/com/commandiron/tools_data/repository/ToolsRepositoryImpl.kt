package com.commandiron.tools_data.repository

import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toToolPresentation
import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ToolsRepositoryImpl(
    private val dao: ToolsDao
): ToolsRepository {
    override suspend fun insertAllTools(tools: List<ToolPresentation>) {
        dao.insertAllTools(tools.map { it.toToolEntity() })
    }

    override fun getAllTools(): Flow<List<ToolPresentation>> = flow {
        val tools = dao
            .getAllTools()
            .map { entities ->
                entities.toToolPresentation()
            }
        emit(tools)
    }

    override suspend fun insertTool(toolPresentation: ToolPresentation) {
        dao.insertTool(toolPresentation.toToolEntity())
    }
}