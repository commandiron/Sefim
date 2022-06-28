package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class GetUserLastKnownPosition(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() = repository.getUserLastKnownPosition()
}