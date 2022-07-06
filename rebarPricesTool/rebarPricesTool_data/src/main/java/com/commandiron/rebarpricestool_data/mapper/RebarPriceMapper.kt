package com.commandiron.rebarpricestool_data.mapper

import com.commandiron.rebarpricestool_data.local.entity.RebarPriceEntity
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun RebarPriceEntity.toRebarPrice(): RebarPrice {
    return RebarPrice(
        id = id,
        date = LocalDateTime.parse(date),
        city = city,
        q8mmPrice = q8mmPrice,
        q10mmPrice = q10mmPrice,
        q1232mmPrice = q1232mmPrice
    )
}

fun RebarPrice.toRebarPriceEntity(): RebarPriceEntity {
    return RebarPriceEntity(
        id = id,
        date = LocalDateTime.now().toString(),
        city = city,
        q8mmPrice = q8mmPrice,
        q10mmPrice = q10mmPrice,
        q1232mmPrice = q1232mmPrice
    )
}