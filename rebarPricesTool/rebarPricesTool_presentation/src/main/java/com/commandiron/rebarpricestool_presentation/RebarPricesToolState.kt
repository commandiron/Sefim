package com.commandiron.rebarpricestool_presentation

import com.commandiron.rebarpricestool_domain.model.RebarPrice

data class RebarPricesToolState(
    val rebarPrices: List<RebarPrice>? = null
)

