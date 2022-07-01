package com.commandiron.sefim.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val title: String? = null,
    var route: String,
    var selectedImageVector: ImageVector? = null,
    var unSelectedImageVector: ImageVector? = null
){
    object HotSplashScreen : NavigationItem(
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
    object News : NavigationItem(
        title = "Haberler",
        route = "news",
        selectedImageVector = Icons.Default.Feed,
        unSelectedImageVector = Icons.Outlined.Feed
    )
    object RebarPricesTool : NavigationItem(
        route = "rebarPrices"
    )
    object WeatherTool : NavigationItem(
        route = "weather"
    )
    object AeratedConcretePalletCalculatorTool : NavigationItem(
        route = "aeratedConcretePalletCalculator"
    )
    object RebarCalculatorTool : NavigationItem(
        route = "rebarCalculator"
    )
}
