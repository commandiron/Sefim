package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository

class GetRecommendedTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(): List<Tool> {
        return repository.getAllTools().filter {
            !it.isFavorite && ToolTag.SOON !in (it.toolTags)
        }
    }
}