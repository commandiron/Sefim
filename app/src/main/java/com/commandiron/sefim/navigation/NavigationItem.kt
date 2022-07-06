package com.commandiron.sefim.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.commandiron.core.model.aeratedConcTool
import com.commandiron.core.model.rebarCalculatorTool
import com.commandiron.core.model.rebarPricesTool
import com.commandiron.core.model.weatherTool
import com.commandiron.core_ui.util.Strings.Turkish.MAIN_SCREEN
import com.commandiron.core_ui.util.Strings.Turkish.NEWS
import com.commandiron.core_ui.util.Strings.Turkish.TOOLS

sealed class NavigationItem(
    val title: String? = null,
    var route: String,
    var selectedImageVector: ImageVector? = null,
    var unSelectedImageVector: ImageVector? = null
){
    object HotSplashScreen : NavigationItem(
        route = "hotSplash"
    )
    object HomeScreen : NavigationItem(
        title = MAIN_SCREEN,
        route = "home",
        selectedImageVector = Icons.Default.Home,
        unSelectedImageVector = Icons.Outlined.Home
    )
    object Tools : NavigationItem(
        title = TOOLS,
        route = "tools",
        selectedImageVector = Icons.Default.Calculate,
        unSelectedImageVector = Icons.Outlined.Calculate
    )
    object News : NavigationItem(
        title = NEWS,
        route = "news",
        selectedImageVector = Icons.Default.Feed,
        unSelectedImageVector = Icons.Outlined.Feed
    )
    object RebarPricesTool : NavigationItem(
        title = rebarPricesTool.title,
        route = rebarPricesTool.route
    )
    object WeatherTool : NavigationItem(
        title = weatherTool.title,
        route = weatherTool.route
    )
    object AeratedConcretePalletCalculatorTool : NavigationItem(
        title = aeratedConcTool.title,
        route = aeratedConcTool.route
    )
    object RebarCalculatorTool : NavigationItem(
        title = rebarCalculatorTool.title,
        route = rebarCalculatorTool.route
    )
}
