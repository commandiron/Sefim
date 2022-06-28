package com.commandiron.sefim.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Save
import androidx.compose.ui.graphics.vector.ImageVector
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.defaultTools

sealed class NavigationItem(
    var title:String,
    var route: String,
    var selectedImageVector: ImageVector? = null,
    var unSelectedImageVector: ImageVector? = null
){
    object HotSplashScreen : NavigationItem(
        title = "Splash Screen",
        route = "splashScreen"
    )
    object HomeScreen : NavigationItem(
        title = "Ana Ekran",
        route = "homeScreen",
        selectedImageVector = Icons.Default.Home,
        unSelectedImageVector = Icons.Outlined.Home
    )
    object Tools : NavigationItem(
        title = "Araçlar",
        route = "tools",
        selectedImageVector = Icons.Default.Calculate,
        unSelectedImageVector = Icons.Outlined.Calculate
    )
    object Weather : NavigationItem(
        title = defaultTools[0].title,
        route = defaultTools[0].route
    )
    object MyCalculations : NavigationItem(
        title = "Hesaplamalarım",
        route = "myCalculations",
        selectedImageVector = Icons.Default.Save,
        unSelectedImageVector = Icons.Outlined.Save
    )
    object News : NavigationItem(
        title = "Haberler",
        route = "news",
        selectedImageVector = Icons.Default.Feed,
        unSelectedImageVector = Icons.Outlined.Feed
    )
}
