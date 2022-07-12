package com.commandiron.roughconstructioncosttool_presentation

sealed class RoughConstructionCostUserEvent{
    object Back: RoughConstructionCostUserEvent()
    object KeyboardDone: RoughConstructionCostUserEvent()

    data class FormWorkQuantityTextChange(val text: String): RoughConstructionCostUserEvent()
    data class FormWorkUnitPriceTextChange(val text: String): RoughConstructionCostUserEvent()

    data class RebarQuantityTextChange(val text: String): RoughConstructionCostUserEvent()
    data class RebarUnitPriceTextChange(val text: String): RoughConstructionCostUserEvent()

    data class ConcreteQuantityTextChange(val text: String): RoughConstructionCostUserEvent()
    data class ConcreteUnitPriceTextChange(val text: String): RoughConstructionCostUserEvent()
}
