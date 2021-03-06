package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository

class GetNewestTool(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(): Tool {
        return repository.getAllTools().filter {
            !it.toolTags.contains(ToolTag.SOON)
        }.last()
    }
}