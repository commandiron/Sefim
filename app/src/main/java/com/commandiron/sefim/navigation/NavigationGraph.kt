package com.commandiron.sefim.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.commandiron.aeratedconctool_presentation.AeratedConcToolScreen
import com.commandiron.core_ui.util.LocalSystemUiController
import com.commandiron.news_presentation.NewsScreen
import com.commandiron.rebarcalculatortool_presentation.RebarCalculatorScreen
import com.commandiron.rebarpricestool_presentation.RebarPricesScreen
import com.commandiron.roughconstructioncosttool_presentation.RoughConstructionCostScreen
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.presentation.hot_splash.HotSplashScreen
import com.commandiron.tools_presentation.tools.ToolsScreen
import com.commandiron.weathertool_presentation.WeatherScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun NavigationGraph(
    navController: NavHostController,
    shouldShowHotSplash: Boolean,
    showSnackBar:(String) -> Unit
) {
    val systemUiController = LocalSystemUiController.current
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.background
    )
    AnimatedNavHost(
        navController = navController,
        startDestination = if(shouldShowHotSplash) {
            NavigationItem.HotSplashScreen.route
        } else {
            NavigationItem.HomeScreen.route
        }
    ){
        composable(
            route = NavigationItem.HotSplashScreen.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> null
                }
            }
        ){
            HotSplashScreen (
                onFinish = {
                    navController.popBackStack()
                    navController.navigate(NavigationItem.HomeScreen.route)
                }
            )
        }
        composable(
            route = NavigationItem.HomeScreen.route,
            enterTransition = {
                when(initialState.destination.route){
                    NavigationItem.Tools.route -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.Tools.route -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            }
        ){
            HomeScreen(
                navigateTo = {
                    navController.navigate(it)
                },
                showSnackbar = showSnackBar
            )
        }
        composable(
            route = NavigationItem.Tools.route,
            enterTransition = {
                when(initialState.destination.route){
                    NavigationItem.HomeScreen.route -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    NavigationItem.News.route -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.HomeScreen.route -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                    NavigationItem.News.route -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            }
        ){
            ToolsScreen(
                navigateTo = {
                    navController.navigate(it)
                },
                showSnackbar = showSnackBar
            )
        }
        composable(
            route = NavigationItem.News.route,
            enterTransition = {
                when(initialState.destination.route){
                    NavigationItem.Tools.route -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.Tools.route -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            }
        ){
            NewsScreen()
        }
        composable(
            route = NavigationItem.RebarPricesTool.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            }
        ){
            RebarPricesScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = NavigationItem.WeatherTool.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            }
        ){
            WeatherScreen(
                navigateUp = {
                    navController.navigateUp()
                },
                showSnackbar = showSnackBar
            )
        }
        composable(
            route = NavigationItem.AeratedConcretePalletCalculatorTool.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            }
        ){
            AeratedConcToolScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = NavigationItem.RebarCalculatorTool.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            }
        ){
            RebarCalculatorScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = NavigationItem.RoughConstructionCostTool.route,
            enterTransition = {
                when(initialState.destination.route){
                    else -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(700)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    else -> slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(700)
                    )
                }
            }
        ){
            RoughConstructionCostScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}