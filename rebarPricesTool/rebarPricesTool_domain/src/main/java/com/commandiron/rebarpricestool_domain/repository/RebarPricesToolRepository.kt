package com.commandiron.rebarpricestool_domain.repository

import com.commandiron.core.util.Response
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import kotlinx.coroutines.flow.Flow

interface RebarPricesToolRepository {
    suspend fun getRebarPrices(): Flow<Response<List<RebarPrice>>>
}