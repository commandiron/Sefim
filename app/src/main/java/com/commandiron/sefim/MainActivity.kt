
package com.commandiron.sefim

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.commandiron.AppViewModel
import com.commandiron.sefim.core.getProvidedValues
import com.commandiron.sefim.navigation.BottomNavigation
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.sefim.presentation.MyCalculationsScreen
import com.commandiron.sefim.presentation.NewsScreen
import com.commandiron.sefim.presentation.ToolsScreen
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.presentation.hot_splash.HotSplashScreen
import com.commandiron.sefim.ui.theme.SefimTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepVisibleCondition{
            viewModel.state.isColdSplashScreenVisible
        }
        setContent {
            MainContent()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    SefimTheme {
        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()
        CompositionLocalProvider(
            values = getProvidedValues(
                navController = navController,
                systemUiController = systemUiController
            )
        ) {
            Scaffold(
                bottomBar = { BottomNavigation() }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationItem.HotSplashScreen.route
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
                        HomeScreen()
                    }
                    composable(NavigationItem.Tools.route){
                        ToolsScreen()
                    }
                    composable(NavigationItem.MyCalculations.route){
                        MyCalculationsScreen()
                    }
                    composable(NavigationItem.News.route){
                        NewsScreen()
                    }

                }
            }
        }
    }
}
