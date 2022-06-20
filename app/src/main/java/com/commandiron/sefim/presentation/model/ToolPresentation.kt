package com.commandiron.sefim.presentation.model

import androidx.compose.ui.graphics.Color
import com.commandiron.sefim.R

data class ToolPresentation(
    val id: Int? = null,
    val title: String,
    val resources : Int,
    val iconBackground: Color? = null,
    val iconTint: Color? = null,
    val titleColor: Color? = null,
    val toolTags: List<ToolTag> = listOf(),
    val onUnFavoriteState: Boolean = false
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
        title = "Üçgen Alan Hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFF9bf6ff),
    ),
    ToolPresentation(
        title = "Beton Hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFa0c4ff)
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFbdb2ff)
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.duvar_metraj_hesaplayici,
        iconBackground = Color(0xFFffc6ff)
    )
)
