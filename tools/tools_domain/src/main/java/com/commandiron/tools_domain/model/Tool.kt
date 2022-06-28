package com.commandiron.tools_domain.model

import com.commandiron.core.R

data class Tool(
    val id: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean = false,
    val toolTags: List<ToolTag> = listOf(),
    val route: String
)

val defaultTools = listOf(
    Tool(
        id = 0,
        title = "Hava Durumu",
        resources = R.drawable.cloudy,
        toolTags = listOf(ToolTag.NEW),
        route = "weather"
    ),
    Tool(
        id = 1,
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.brick_wall,
        toolTags = listOf(ToolTag.NEW),
        route = "wallQuantityCalculator"
    ),
    Tool(
        id = 2,
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.pallet,
        toolTags = listOf(ToolTag.NEW),
        route = "aeratedConcretePalletCalculator"
    ),
    Tool(
        id = 3,
        title = "İnşaat Demiri Ağırlık Hesaplayıcı",
        resources = R.drawable.iron_bar,
        toolTags = listOf(ToolTag.NEW),
        route = "aerated concrete pallet calculator"
    ),
    Tool(
        id = 4,
        title = "Kaba İnşaat Maliyet Hesaplayıcı",
        resources = R.drawable.construction,
        toolTags = listOf(ToolTag.SOON),
        route = "roughConstructionCostCalculator"
    ),
    Tool(
        id = 5,
        title = "Kamera ile uzunluk ölçümü",
        resources = R.drawable.tape_measure,
        toolTags = listOf(ToolTag.AR, ToolTag.SOON),
        route = "lengthMeasurementWithCamera"
    )
)