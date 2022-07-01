package com.commandiron.tools_presentation.rebarCalculatorTool

import com.commandiron.tools_presentation.rebarCalculatorTool.model.RebarCalculatorItem

data class RebarCalculatorToolState(
    val rebarCalculatorItems: List<RebarCalculatorItem> = listOf(RebarCalculatorItem()),
    val grandResult: String = "--",
    val grandResultUnit: String = "kg"
)

