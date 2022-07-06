package com.commandiron.rebarpricestool_domain.model

import java.time.LocalDateTime

data class RebarPrice(
    val id: Int,
    val date: LocalDateTime,
    val city: String,
    val q8mmPrice: String,
    val q10mmPrice: String,
    val q1232mmPrice: String,
)