package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetFavoriteTools(
    private val repository: ToolsRepository
) {
    operator fun invoke() : Flow<List<ToolPresentation>> {
        return repository.getAllTools().map { tools ->
            tools.filter { it.isFavorite }
        }
    }
}