
package com.commandiron.sefim

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.commandiron.core_ui.components.AppLogoWithName
import com.commandiron.core_ui.components.FixedAppDecoration
import com.commandiron.core_ui.getProvidedValuesOnApp
import com.commandiron.core_ui.presentation.bottom_navigation.BottomNavigation
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.tools_presentation.MyCalculationsScreen
import com.commandiron.tools_presentation.ToolsScreen
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.presentation.hot_splash.HotSplashScreen
import com.commandiron.core_ui.theme.SefimTheme
import com.commandiron.news_presentation.NewsScreen
import com.commandiron.sefim.navigation.bottomNavigate
import com.commandiron.sefim.navigation.currentRoute
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
                            navController.bottomNavigate(route = it)
                        }
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.background
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
