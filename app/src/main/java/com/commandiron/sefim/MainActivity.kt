package com.commandiron.sefim

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.commandiron.core.domain.preferences.Preferences
import com.commandiron.core_ui.getProvidedValuesOnApp
import com.commandiron.core_ui.theme.SefimTheme
import com.commandiron.sefim.navigation.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepVisibleCondition{
            viewModel.state.isColdSplashScreenVisible
        }
        val shouldShowHotSplash = preferences.loadShouldShowHotSplash()
        setContent {
            SefimTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
                val navController = rememberAnimatedNavController()
                val systemUiController = rememberSystemUiController()
                CompositionLocalProvider(
                    values = getProvidedValuesOnApp(
                        permissionsState = permissionsState,
                        navController = navController,
                        systemUiController = systemUiController
                    )
                ) {
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
                        NavigationGraph(
                            navController = navController,
                            shouldShowHotSplash = shouldShowHotSplash
                        )
                        BackHandler {
                            navController.popBackStack()
                            navController.navigate(NavigationItem.HomeScreen.route)
                        }
                    }
                }
            }
        }
    }
}
