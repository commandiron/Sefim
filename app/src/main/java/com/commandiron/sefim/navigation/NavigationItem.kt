package com.commandiron.sefim.navigation

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
        selectedImageResource = R.drawable.homepage_filled,
        unSelectedImageResource = R.drawable.homepage_outlined,
    )
    object Tools : NavigationItem(
        title = TOOLS,
        route = "tools",
        selectedImageResource = R.drawable.tool_box_filled,
        unSelectedImageResource = R.drawable.tool_box_outlined
    )
    object News : NavigationItem(
        title = NEWS,
        route = "news",
        selectedImageResource = R.drawable.newspaper_filled,
        unSelectedImageResource = R.drawable.newspaper_outlined
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
