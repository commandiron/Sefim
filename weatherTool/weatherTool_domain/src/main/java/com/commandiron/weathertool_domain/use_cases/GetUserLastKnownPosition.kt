package com.commandiron.weathertool_domain.use_cases

import com.commandiron.weathertool_domain.repository.WeatherToolRepository

class GetUserLastKnownPosition(
    private val repository: WeatherToolRepository
) {
    suspend operator fun invoke() = repository.getUserLastKnownPosition()
}