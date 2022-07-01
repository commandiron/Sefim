package com.commandiron.sefim.navigation

import android.Manifest
import android.nfc.tech.NfcV
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.commandiron.core_ui.LocalPermissionsState
import com.commandiron.news_presentation.NewsScreen
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.presentation.hot_splash.HotSplashScreen
import com.commandiron.tools_presentation.aeratedConcTool.AeratedConcToolScreen
import com.commandiron.tools_presentation.my_calculations.MyCalculationsScreen
import com.commandiron.tools_presentation.tools.ToolsScreen
import com.commandiron.tools_presentation.weather.WeatherScreen
import com.commandiron.tools_presentation.weather.WeatherUserEvent
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun NavigationGraph(
    navController: NavHostController,
    shouldShowHotSplash: Boolean
) {
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
                }
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
                    NavigationItem.MyCalculations.route -> slideIntoContainer(
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
                    NavigationItem.MyCalculations.route -> slideOutOfContainer(
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
                }
            )
        }
        composable(
            route = NavigationItem.MyCalculations.route,
            enterTransition = {
                when(initialState.destination.route){
                    NavigationItem.Tools.route -> slideIntoContainer(
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
                    NavigationItem.Tools.route -> slideOutOfContainer(
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
            MyCalculationsScreen()
        }
        composable(
            route = NavigationItem.News.route,
            enterTransition = {
                when(initialState.destination.route){
                    NavigationItem.MyCalculations.route -> slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    NavigationItem.MyCalculations.route -> slideOutOfContainer(
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
            route = NavigationItem.Weather.route,
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
                }
            )
        }
        composable(
            route = NavigationItem.AeratedConcTool.route,
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
    }
}