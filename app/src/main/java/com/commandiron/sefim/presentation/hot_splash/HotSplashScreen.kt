package com.commandiron.sefim.presentation.hot_splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.components.AppLogoWithName
import kotlinx.coroutines.delay

@Composable
fun HotSplashScreen(
    viewModel: HotSplashViewModel = hiltViewModel(),
    onFinish:() -> Unit
) {
    LaunchedEffect(key1 = true){
        delay(5000)
        onFinish()
    }
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppLogoWithName(
                modifier = Modifier.fillMaxWidth(0.2f),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}