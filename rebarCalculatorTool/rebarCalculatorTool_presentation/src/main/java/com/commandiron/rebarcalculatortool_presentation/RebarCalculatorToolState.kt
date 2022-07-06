package com.commandiron.rebarcalculatortool_presentation

import com.commandiron.rebarcalculatortool_presentation.model.RebarCalculatorItem

data class RebarCalculatorToolState(
    val rebarCalculatorItems: List<RebarCalculatorItem> = listOf(RebarCalculatorItem()),
    val grandResult: String = "--",
    val grandResultUnit: String = "kg",
    val grandResult2: String = "--",
    val grandResult2Unit: String = "ton"
)

