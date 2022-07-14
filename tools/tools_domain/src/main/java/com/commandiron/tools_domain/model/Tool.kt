package com.commandiron.tools_domain.model

import com.commandiron.tools_domain.R

data class Tool(
    val id: Int,
    val queue: Int,
    val title: String,
    val icon : Int,
    val isFavorite: Boolean = false,
    val toolTags: List<ToolTag> = listOf(),
    val route: String
)

val rebarPricesTool = Tool(
    id = 0,
    queue = 0,
    title = "İnşaat Demiri Fiyatları",
    icon = R.drawable.prices,
    toolTags = listOf(ToolTag.NEW),
    route = "rebarPrices"
)
val weatherTool = Tool(
    id = 1,
    queue = 1,
    title = "Hava Durumu",
    icon = R.drawable.cloudy,
    toolTags = listOf(ToolTag.NEW),
    route = "weather"
)
val aeratedConcTool = Tool(
    id = 2,
    queue = 2,
    title = "Gazbeton Hesaplama Aracı",
    icon = R.drawable.pallet,
    toolTags = listOf(ToolTag.NEW),
    route = "aeratedConc"
)
val rebarCalculatorTool = Tool(
    id = 3,
    queue = 3,
    title = "İnşaat Demiri Metraj Hesaplayıcı",
    icon = R.drawable.iron_bar,
    toolTags = listOf(ToolTag.NEW),
    route = "rebarCalculator"
)
val roughConstructionCostCalculatorTool = Tool(
    id = 4,
    queue = 4,
    title = "Kaba İnşaat Maliyet Hesaplayıcı",
    icon = R.drawable.construction,
    toolTags = listOf(ToolTag.NEW),
    route = "roughConstructionCost"
)
val lengthMeasureWithCamTool = Tool(
    id = 5,
    queue = 5,
    title = "Kamera ile uzunluk ölçümü",
    icon = R.drawable.tape_measure,
    toolTags = listOf(ToolTag.AR, ToolTag.SOON),
    route = ""
)