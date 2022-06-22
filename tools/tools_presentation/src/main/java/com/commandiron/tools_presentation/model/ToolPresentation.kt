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
    val toolTags: List<ToolTag> = listOf(),
    val wobblingEffectEnabled: Boolean = false
)

val defaultTools = listOf(
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffadad),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.gazbeton_palet_hesaplayici,
        iconBackground = Color(0xFFffd6a5),
        toolTags = listOf(ToolTag.NEW, ToolTag.LOCKED)
    ),
    ToolPresentation(
        title = "İnşaat Demiri Ağırlık Hesaplayıcı",
        resources = R.drawable.insaat_demir_agirlik_hesapcayici,
        iconBackground = Color(0xFFfdffb6),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Kaba İnşaat Maliyet Hesaplayıcı",
        resources = R.drawable.kaba_insaat_maliyet_hesaplayici,
        iconBackground = Color(0xFFcaffbf),
        toolTags = listOf(ToolTag.SOON)
    ),
    ToolPresentation(
        title = "Kamera ile uzunluk ölçümü",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFF9bf6ff),
        toolTags = listOf(ToolTag.CAM)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFa0c4ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFbdb2ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffc6ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFa0c4ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFbdb2ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffc6ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFa0c4ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFbdb2ff)
    ),
    ToolPresentation(
        title = "Test Tool hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffc6ff)
    )
)

val defaultFavorites = listOf(
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffadad),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.gazbeton_palet_hesaplayici,
        iconBackground = Color(0xFFffd6a5),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "İnşaat Demiri Ağırlık Hesaplayıcı",
        resources = R.drawable.insaat_demir_agirlik_hesapcayici,
        iconBackground = Color(0xFFfdffb6),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Kaba İnşaat Maliyet Hesaplayıcı",
        resources = R.drawable.kaba_insaat_maliyet_hesaplayici,
        iconBackground = Color(0xFFcaffbf),
        toolTags = listOf(ToolTag.SOON)
    ),
    ToolPresentation(
        title = "Kamera ile uzunluk ölçümü",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFF9bf6ff),
        toolTags = listOf(ToolTag.CAM)
    ),
)
