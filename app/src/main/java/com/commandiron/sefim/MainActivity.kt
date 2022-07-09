package com.commandiron.sefim

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.commandiron.core.domain.preferences.Preferences
import com.commandiron.core_ui.components.CustomSnackbar
import com.commandiron.core_ui.theme.SefimTheme
import com.commandiron.core_ui.util.Strings.Turkish.SNACKBAR_CLOSE_ACTION_TEXT
import com.commandiron.core_ui.util.getProvidedValuesOnApp
import com.commandiron.core_ui.util.rememberWindowInfo
import com.commandiron.sefim.navigation.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
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
                val windowInfo = rememberWindowInfo()
                val coroutineScope = rememberCoroutineScope()
                val snackbarHostState = remember {SnackbarHostState()}
                CompositionLocalProvider(
                    values = getProvidedValuesOnApp(
                        permissionsState = permissionsState,
                        navController = navController,
                        systemUiController = systemUiController,
                        windowInfo = windowInfo
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
                        snackbarHost = {
                            SnackbarHost(snackbarHostState){ data ->
                                CustomSnackbar(data)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.background
                    ) {
                        NavigationGraph(
                            navController = navController,
                            shouldShowHotSplash = shouldShowHotSplash,
                            showSnackBar = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(it, SNACKBAR_CLOSE_ACTION_TEXT)

                                }
                            }
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
