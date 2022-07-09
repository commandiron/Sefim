package com.commandiron.sefim.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Assistant
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.commandiron.core_ui.util.Strings.Turkish.MAIN_SCREEN
import com.commandiron.core_ui.util.Strings.Turkish.NEWS
import com.commandiron.core_ui.util.Strings.Turkish.TOOLS
import com.commandiron.core_ui.R
import com.commandiron.tools_domain.model.aeratedConcTool
import com.commandiron.tools_domain.model.rebarCalculatorTool
import com.commandiron.tools_domain.model.rebarPricesTool
import com.commandiron.tools_domain.model.weatherTool

sealed class NavigationItem(
    val title: String? = null,
    var route: String,
    var selectedImageResource: Int? = null,
    var unSelectedImageResource: Int? = null
){
    object HotSplashScreen : NavigationItem(
        route = "hotSplash"
    )
    object HomeScreen : NavigationItem(
        title = MAIN_SCREEN,
        route = "home",
        selectedImageResource = R.drawable.home_filled,
        unSelectedImageResource = R.drawable.home_outlined,
    )
    object Tools : NavigationItem(
        title = TOOLS,
        route = "tools",
        selectedImageResource = R.drawable.tool_box_filled,
        unSelectedImageResource = R.drawable.tool_box_outlined,
    )
    object News : NavigationItem(
        title = NEWS,
        route = "news",
        selectedImageResource = R.drawable.news_filled,
        unSelectedImageResource = R.drawable.news_outlined,
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
