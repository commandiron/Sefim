package com.commandiron.core.model

import com.commandiron.core.R

data class Tool(
    val id: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean = false,
    val toolTags: List<ToolTag> = listOf(),
    val route: String
)

val rebarPricesTool = Tool(
    id = 0,
    title = "İnşaat Demiri Fiyatları",
    resources = R.drawable.prices,
    toolTags = listOf(ToolTag.NEW),
    route = "rebarPrices"
)
val weatherTool = Tool(
    id = 1,
    title = "Hava Durumu",
    resources = R.drawable.cloudy,
    toolTags = listOf(ToolTag.NEW),
    route = "weather"
)
val aeratedConcTool = Tool(
    id = 2,
    title = "Gazbeton Hesaplama Aracı",
    resources = R.drawable.pallet,
    toolTags = listOf(ToolTag.NEW),
    route = "aeratedConc"
)
val rebarCalculatorTool = Tool(
    id = 3,
    title = "İnşaat Demiri Metraj Hesaplayıcı",
    resources = R.drawable.iron_bar,
    toolTags = listOf(ToolTag.NEW),
    route = "rebarCalculator"
)
val roughConstructionCostCalculatorTool = Tool(
    id = 4,
    title = "Kaba İnşaat Maliyet Hesaplayıcı",
    resources = R.drawable.construction,
    toolTags = listOf(ToolTag.SOON),
    route = ""
)
val lengthMeasureWithCamTool = Tool(
    id = 5,
    title = "Kamera ile uzunluk ölçümü",
    resources = R.drawable.tape_measure,
    toolTags = listOf(ToolTag.AR, ToolTag.SOON),
    route = ""
)

val allToolsInApp = listOf(
    rebarPricesTool,
    weatherTool,
    aeratedConcTool,
    rebarCalculatorTool,
    roughConstructionCostCalculatorTool,
    lengthMeasureWithCamTool
)