package com.commandiron.rebarpricestool_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RebarPriceEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val city: String,
    val q8mmPrice: String,
    val q10mmPrice: String,
    val q1232mmPrice: String,

)
