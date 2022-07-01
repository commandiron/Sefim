package com.commandiron.core_ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.systemuicontroller.SystemUiController

val LocalSpacing = compositionLocalOf { Dimensions() }

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceXXSmall: Dp = 2.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val spaceXXLarge: Dp = 128.dp,
    val spaceXXXLarge: Dp = 256.dp,

    val bottomNavigationHeight: Dp = 80.dp,
    val defaultScreenPadding: PaddingValues = PaddingValues(
        start = 24.dp,
        top = spaceMedium,
        end = 24.dp,
        bottom = bottomNavigationHeight
    )
)
val LocalPermissionsState = compositionLocalOf<MultiplePermissionsState> {
    error("No Permission State")
}
val LocalNavController = compositionLocalOf<NavHostController> {
    error("No Nav Controller")
}
val LocalSystemUiController = compositionLocalOf<SystemUiController> {
    error("No System Ui Controller")
}

fun getProvidedValuesOnApp(
    permissionsState: MultiplePermissionsState,
    navController: NavHostController,
    systemUiController: SystemUiController,
): Array<ProvidedValue<*>> {
    return arrayOf(
        LocalSpacing provides Dimensions(),
        LocalNavController provides navController,
        LocalPermissionsState provides permissionsState,
        LocalSystemUiController provides systemUiController
    )
}
