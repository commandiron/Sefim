package com.commandiron.tools_domain.use_cases

import com.commandiron.core.R
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository

class PrepopulateAllToolsInToolsDb(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(){
        val tools = listOf(
            Tool(
                id = 0,
                title = "İnşaat Demiri Fiyatları",
                resources = R.drawable.prices,
                toolTags = listOf(ToolTag.NEW),
                route = "rebarPrices"
            ),
            Tool(
                id = 1,
                title = "Hava Durumu",
                resources = R.drawable.cloudy,
                toolTags = listOf(ToolTag.NEW),
                route = "weather"
            ),
            Tool(
                id = 2,
                title = "Gazbeton Hesaplama Aracı",
                resources = R.drawable.pallet,
                toolTags = listOf(ToolTag.NEW),
                route = "aeratedConcretePalletCalculator"
            ),
            Tool(
                id = 3,
                title = "İnşaat Demiri Metraj Hesaplayıcı",
                resources = R.drawable.iron_bar,
                toolTags = listOf(ToolTag.NEW),
                route = "rebarCalculator"
            ),
            Tool(
                id = 4,
                title = "Kaba İnşaat Maliyet Hesaplayıcı",
                resources = R.drawable.construction,
                toolTags = listOf(ToolTag.SOON),
                route = ""
            ),
            Tool(
                id = 5,
                title = "Kamera ile uzunluk ölçümü",
                resources = R.drawable.tape_measure,
                toolTags = listOf(ToolTag.AR, ToolTag.SOON),
                route = ""
            )
        )
        repository.insertAllTools(tools)
    }
}