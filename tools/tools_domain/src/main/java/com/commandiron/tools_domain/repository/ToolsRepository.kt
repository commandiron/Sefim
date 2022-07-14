package com.commandiron.tools_domain.repository

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import kotlinx.coroutines.flow.Flow

interface ToolsRepository {
    suspend fun checkDatabaseIsExist(): Boolean
    suspend fun getAllTools(): List<Tool>
    suspend fun insertTool(tool: Tool): Flow<Response<Long>>
}