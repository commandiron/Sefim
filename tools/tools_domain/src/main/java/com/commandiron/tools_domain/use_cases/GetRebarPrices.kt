package com.commandiron.tools_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.RebarPrice
import com.commandiron.tools_domain.repository.ToolsRepository
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class GetRebarPrices(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke() : Flow<Response<List<RebarPrice>>> = repository.getRebarPrices()

}