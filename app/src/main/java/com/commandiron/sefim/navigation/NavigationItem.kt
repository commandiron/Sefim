package com.commandiron.sefim.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PanTool
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    var title:String,
    var selectedImageVector: ImageVector,
    var unSelectedImageVector: ImageVector,
)

val defaultNavigationItems = listOf(
    NavigationItem(
        title = "Ana Ekran",
        selectedImageVector = Icons.Default.Home,
        unSelectedImageVector = Icons.Outlined.Home
    ),
    NavigationItem(
        title = "Araçlar",
        selectedImageVector = Icons.Default.PanTool,
        unSelectedImageVector = Icons.Outlined.PanTool
    ),
    NavigationItem(
        title = "Hesaplamalarım",
        selectedImageVector = Icons.Default.Calculate,
        unSelectedImageVector = Icons.Outlined.Calculate
    ),
    NavigationItem(
        title = "Ayarlar",
        selectedImageVector = Icons.Default.Settings,
        unSelectedImageVector = Icons.Outlined.Settings
    ),
)
