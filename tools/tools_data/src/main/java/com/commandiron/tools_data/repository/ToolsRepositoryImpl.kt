package com.commandiron.tools_data.repository

import com.commandiron.core.util.Response
import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_data.mapper.toTool
import com.commandiron.tools_data.remote.OpenWeatherApi
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToolsRepositoryImpl(
    private val dao: ToolsDao,
    private val api: OpenWeatherApi
): ToolsRepository {
    override suspend fun insertAllTools(tools: List<Tool>) {
        dao.insertAllTools(tools.map { it.toToolEntity() })
    }

    override suspend fun getAllTools(): List<Tool> {
        return dao
            .getAllTools()
            .map { entities ->
                entities.toTool()
            }
    }

    override suspend fun insertTool(tool: Tool) : Flow<Response<Unit>> = flow {
        emit(Response.Loading)
        try {
            dao.insertTool(tool.toToolEntity())
            emit(Response.Success(Unit))
        }catch (e: Exception){
            emit(Response.Error(e.message ?: "SOMETHING HAPPENED"))
        }
    }

    override suspend fun getWeather() {
        val a = api.getWeather(
            latitude = "41.015137",
            longitude = "28.979530"
        )
        println(a)
    }
}