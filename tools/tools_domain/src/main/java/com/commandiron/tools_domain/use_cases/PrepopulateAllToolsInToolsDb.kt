package com.commandiron.tools_domain.use_cases

import com.commandiron.core.R
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.model.defaultTools
import com.commandiron.tools_domain.repository.ToolsRepository

class PrepopulateAllToolsInToolsDb(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(){
        repository.insertAllTools(defaultTools)
    }
}