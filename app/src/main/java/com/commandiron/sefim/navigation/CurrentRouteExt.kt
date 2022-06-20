package com.commandiron.sefim.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavController.currentRoute(): String? {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}