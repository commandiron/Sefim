package com.commandiron.rebarpricestool_domain.use_cases

import com.commandiron.core.util.Response
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import com.commandiron.rebarpricestool_domain.repository.RebarPricesToolRepository
import kotlinx.coroutines.flow.Flow

class GetRebarPrices(
    private val repository: RebarPricesToolRepository
) {
    suspend operator fun invoke() : Flow<Response<List<RebarPrice>>> = repository.getRebarPrices()

}