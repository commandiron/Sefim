package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class GetRecommendedTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(): Flow<Response<List<Tool>>> = flow {
        repository.getAllTools().collect { response ->
            when(response){
                is Response.Error -> { emit(Response.Error(response.message)) }
                Response.Loading -> { emit(Response.Loading) }
                is Response.Success -> {
                    val result = response.data.filter {
                        !it.isFavorite && ToolTag.SOON !in (it.toolTags)
                    }
                    emit(Response.Success(result))
                }
            }
        }
    }
}