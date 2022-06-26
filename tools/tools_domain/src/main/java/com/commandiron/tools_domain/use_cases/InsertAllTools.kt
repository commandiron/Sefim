package com.commandiron.tools_domain.use_cases

import com.commandiron.tools_domain.R
import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.repository.ToolsRepository

class InsertAllTools(
    private val repository: ToolsRepository
) {
    suspend operator fun invoke(){
        val tools = listOf(
            ToolPresentation(
                id = 0,
                title = "Hava Durumu",
                resources = R.drawable.cloudy,
            ),
            ToolPresentation(
                id = 1,
                title = "Duvar Metraj Hesaplayıcı",
                resources = R.drawable.brick_wall,
                toolTags = listOf(ToolTag.NEW)
            ),
            ToolPresentation(
                id = 2,
                title = "Gazbeton Palet Hesaplayıcı",
                resources = R.drawable.pallet,
                toolTags = listOf(ToolTag.NEW, ToolTag.LOCKED)
            ),
            ToolPresentation(
                id = 3,
                title = "İnşaat Demiri Ağırlık Hesaplayıcı",
                resources = R.drawable.iron_bar,
                toolTags = listOf(ToolTag.NEW)
            ),
            ToolPresentation(
                id = 4,
                title = "Kaba İnşaat Maliyet Hesaplayıcı",
                resources = R.drawable.construction,
                toolTags = listOf(ToolTag.SOON)
            ),
            ToolPresentation(
                id = 5,
                title = "Kamera ile uzunluk ölçümü",
                resources = R.drawable.tape_measure,
                toolTags = listOf(ToolTag.AR, ToolTag.SOON)
            ),
            ToolPresentation(
                id = 6,
                title = "Tool For View",
                resources = R.drawable.construction,
            )
        )
        repository.insertAllTools(tools)
    }
}