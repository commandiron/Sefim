package com.commandiron.tools_domain.use_cases

import com.commandiron.core.model.allToolsInApp
import com.commandiron.tools_domain.repository.ToolsRepository

class PrepopulateAllToolsInToolsDb(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(){
        repository.insertAllTools(allToolsInApp)
    }
}