package com.commandiron.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.commandiron.core_ui.LocalSpacing

@Composable
fun FixedAppDecoration() {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.bottomNavigationHeight),
        contentAlignment = Alignment.BottomCenter
    ) {
        AppLogoWithName(
            modifier = Modifier.fillMaxWidth(0.15f),
            alpha = 0.15f,
            color = MaterialTheme.colorScheme.primary
        )
    }
}