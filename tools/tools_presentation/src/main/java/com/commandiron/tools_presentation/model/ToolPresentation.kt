package com.commandiron.tools_presentation.model

import androidx.compose.ui.graphics.Color
import com.commandiron.core_ui.R

data class ToolPresentation(
    val id: Int? = null,
    val title: String,
    val resources : Int,
    val iconBackground: Color? = null,
    val iconTint: Color? = null,
    val titleColor: Color? = null,
    val toolTags: List<ToolTag> = listOf()
)

val defaultTools = listOf(
    ToolPresentation(
        title = "Hava Durumu",
        resources = R.drawable.cloudy,
    ),
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.brick_wall,
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.pallet,
        toolTags = listOf(ToolTag.NEW, ToolTag.LOCKED)
    ),
    ToolPresentation(
        title = "İnşaat Demiri Ağırlık Hesaplayıcı",
        resources = R.drawable.iron_bar,
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Kaba İnşaat Maliyet Hesaplayıcı",
        resources = R.drawable.construction,
        toolTags = listOf(ToolTag.SOON)
    ),
    ToolPresentation(
        title = "Kamera ile uzunluk ölçümü",
        resources = R.drawable.tape_measure,
        toolTags = listOf(ToolTag.CAM, ToolTag.SOON)
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
    ToolPresentation(
        title = "Tool For View",
        resources = R.drawable.construction,
    ),
)

val defaultFavoriteTools = listOf(
    ToolPresentation(
        title = "Hava Durumu",
        resources = R.drawable.cloudy,
    ),
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.brick_wall,
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.pallet,
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "İnşaat Demiri Ağırlık Hesaplayıcı",
        resources = R.drawable.iron_bar,
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Kaba İnşaat Maliyet Hesaplayıcı",
        resources = R.drawable.construction,
        toolTags = listOf(ToolTag.SOON)
    ),
    ToolPresentation(
        title = "Kamera ile uzunluk ölçümü",
        resources = R.drawable.tape_measure,
        toolTags = listOf(ToolTag.CAM, ToolTag.SOON)
    ),
)
