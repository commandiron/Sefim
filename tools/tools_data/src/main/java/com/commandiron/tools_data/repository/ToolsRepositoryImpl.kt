package com.commandiron.tools_data.repository

import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toTool
import com.commandiron.core.model.Tool
import com.commandiron.core.util.Response
import com.commandiron.core.util.Strings
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToolsRepositoryImpl(
    private val dao: ToolsDao,
): ToolsRepository {
    override suspend fun insertAllTools(tools: List<Tool>) {
        dao.insertAllTools(tools.map { it.toToolEntity() })
    }

    override suspend fun getAllTools(): List<Tool> {
        return dao.getAllTools().map { entities -> entities.toTool() }
    }

    override suspend fun insertTool(tool: Tool): Flow<Response<Long>> = flow {
        emit(Response.Loading)
        try {
            val insertedToolId = dao.insertTool(tool.toToolEntity())
            emit(Response.Success(insertedToolId))
        }catch (e: Exception){
            emit(Response.Error(e.message ?: Strings.ExceptionMessages.AN_ERROR_OCCURRED))
        }
    }
}