package com.commandiron.roughconstructioncosttool_presentation

data class RoughConstructionCostState(
    val formWorkQuantityText: String = "",
    val formWorkUnitPriceText: String = "",
    val formWorkResultText: String = "--",

    val rebarQuantityText: String = "",
    val rebarUnitPriceText: String = "",
    val rebarResultText: String = "--",

    val concreteQuantityText: String = "",
    val concreteUnitPriceText: String = "",
    val concreteResultText: String = "--",

    val grandTotalText: String = "--"
)

