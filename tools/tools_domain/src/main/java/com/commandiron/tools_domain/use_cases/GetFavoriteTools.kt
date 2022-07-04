package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetFavoriteTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() : Flow<Response<List<Tool>>> = flow {
        repository.getAllTools().collect{ response ->
            when(response){
                is Response.Error -> { emit(Response.Error(response.message)) }
                Response.Loading -> { emit(Response.Loading) }
                is Response.Success -> {
                    val result = response.data.filter { it.isFavorite }
                    emit(Response.Success(result))
                }
            }
        }
    }
}