package com.commandiron.rebarcalculatortool_presentation

import com.commandiron.core_ui.util.Strings.EMPTY_RESULT_DOUBLE_DASH
import com.commandiron.core_ui.util.Strings.KILOGRAM
import com.commandiron.core_ui.util.Strings.TON
import com.commandiron.rebarcalculatortool_presentation.model.RebarCalculatorItem

data class RebarCalculatorToolState(
    val rebarCalculatorItems: List<RebarCalculatorItem> = listOf(RebarCalculatorItem()),
    val grandResult: String = EMPTY_RESULT_DOUBLE_DASH,
    val grandResultUnit: String = KILOGRAM,
    val grandResult2: String = EMPTY_RESULT_DOUBLE_DASH,
    val grandResult2Unit: String = TON
)

