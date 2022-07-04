package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetNewestTool(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(): Flow<Tool> = flow {
        repository.getAllTools().collect{ response ->
            when(response){
                is Response.Error -> {}
                Response.Loading -> {}
                is Response.Success -> {
                    val allTools = response.data
                    val usableTools = allTools
                        .filter { !it.toolTags.contains(ToolTag.SOON) }
                    emit(usableTools.last())
                }
            }
        }
    }
}