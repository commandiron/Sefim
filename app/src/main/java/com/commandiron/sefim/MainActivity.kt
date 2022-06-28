
package com.commandiron.sefim

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.commandiron.core.domain.preferences.Preferences
import com.commandiron.core_ui.components.FixedAppDecoration
import com.commandiron.core_ui.getProvidedValuesOnApp
import com.commandiron.sefim.navigation.BottomNavigation
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.tools_presentation.my_calculations.MyCalculationsScreen
import com.commandiron.tools_presentation.tools.ToolsScreen
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.presentation.hot_splash.HotSplashScreen
import com.commandiron.core_ui.theme.SefimTheme
import com.commandiron.news_presentation.NewsScreen
import com.commandiron.sefim.navigation.bottomNavigate
import com.commandiron.sefim.navigation.currentRoute
import com.commandiron.tools_presentation.weather.WeatherScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepVisibleCondition{
            viewModel.state.isColdSplashScreenVisible
        }
        val shouldShowHotSplash = preferences.loadShouldShowHotSplash()
        setContent {
            MainContent(shouldShowHotSplash )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(shouldShowHotSplash : Boolean) {
    SefimTheme {
        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()
        CompositionLocalProvider(
            values = getProvidedValuesOnApp(
                navController = navController,
                systemUiController = systemUiController
            )
        ) {
            FixedAppDecoration()
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        currentRoute = navController.currentRoute(),
                        onBottomNavItemClick = {
                            navController.popBackStack()
                            navController.bottomNavigate(route = it)
                        }
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.background
            ) {
                NavHost(
                    navController = navController,
                    startDestination = if(shouldShowHotSplash) {
                        NavigationItem.HotSplashScreen.route
                    } else {
                        NavigationItem.HomeScreen.route
                    }
                ){
                    composable(NavigationItem.HotSplashScreen.route){
                        HotSplashScreen (
                            onFinish = {
                                navController.popBackStack()
                                navController.navigate(NavigationItem.HomeScreen.route)
                            }
                        )
                    }
                    composable(NavigationItem.HomeScreen.route){
                        HomeScreen(
                            navigateTo = {
                                navController.navigate(it)
                            }
                        )
                    }
                    composable(NavigationItem.Tools.route){
                        ToolsScreen(
                            navigateTo = {
                                navController.navigate(it)
                            }
                        )
                    }
                    composable(NavigationItem.Weather.route){
                        WeatherScreen(
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable(NavigationItem.MyCalculations.route){
                        MyCalculationsScreen()
                    }
                    composable(NavigationItem.News.route){
                        NewsScreen()
                    }
                }
                BackHandler {
                    navController.popBackStack()
                    navController.navigate(NavigationItem.HomeScreen.route)
                }
            }
        }
    }
}
