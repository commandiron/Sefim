package com.commandiron.tools_domain.use_cases

import com.commandiron.core.model.Tool
import com.commandiron.core.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository

class GetNewestTool(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(): Tool {
        return repository.getAllTools().filter { !it.toolTags.contains(ToolTag.SOON) }.last()
    }
}