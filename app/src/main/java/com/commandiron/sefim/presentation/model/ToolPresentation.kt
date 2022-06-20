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
//    val isNew: Boolean = false,
//    val isLocked: Boolean = false,
//    val isCamTool: Boolean = false
)

val defaultTools = listOf(
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFffadad),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFffd6a5),
        toolTags = listOf(ToolTag.NEW, ToolTag.LOCKED)
    ),
    ToolPresentation(
        title = "Kare Alan Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFfdffb6),
        toolTags = listOf(ToolTag.CAM)
    ),
    ToolPresentation(
        title = "Dikdörtgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFcaffbf),
    ),
    ToolPresentation(
        title = "Üçgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFF9bf6ff),
    ),
    ToolPresentation(
        title = "Beton Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFa0c4ff)
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFbdb2ff)
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFffc6ff)
    ),
    ToolPresentation(
        title = "Kare Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Dikdörtgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Üçgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Beton Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Kare Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Dikdörtgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Üçgen Alan Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Beton Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    )
)

val defaultFavories = listOf(
    ToolPresentation(
        title = "Duvar Metraj Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFffadad),
        toolTags = listOf(ToolTag.NEW)
    ),
    ToolPresentation(
        title = "Gazbeton Palet Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFffd6a5),
        toolTags = listOf(ToolTag.NEW, ToolTag.LOCKED)
    ),
    ToolPresentation(
        title = "Kare Alan Hesaplayıcı",
        resources = R.drawable.tool_wall,
        iconBackground = Color(0xFFfdffb6),
        toolTags = listOf(ToolTag.CAM)
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Hafriyat Hesaplayıcı",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    ),
    ToolPresentation(
        title = "Birim Çevirici",
        resources = R.drawable.tool_wall
    )
)
