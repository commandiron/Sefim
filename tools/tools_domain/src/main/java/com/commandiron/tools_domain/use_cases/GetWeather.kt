package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.repository.ToolsRepository

class GetWeather(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() {
        repository.getWeather()
    }
}