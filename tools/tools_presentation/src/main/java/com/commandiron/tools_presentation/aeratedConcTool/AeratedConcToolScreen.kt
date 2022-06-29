package com.commandiron.tools_presentation.aeratedConcTool

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.tools_presentation.aeratedConcTool.components.converters.CubicMetersPalletConverter
import com.commandiron.tools_presentation.aeratedConcTool.components.converters.PiecePalletConverter
import com.commandiron.tools_presentation.aeratedConcTool.components.converters.SquareMetersPalletConverter

@Composable
fun AeratedConcToolScreen(
    viewModel: AeratedConcToolViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.HideKeyboard -> {
                    keyboardController?.hide()
                }
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.defaultHorizontalScreenPadding,
                top = spacing.spaceMedium,
                end = spacing.defaultHorizontalScreenPadding,
                bottom = spacing.bottomNavigationHeight
            )
    ) {
        Text(
            text = "Gazbeton Hesaplama Aracı",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        SquareMetersPalletConverter(state, viewModel)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        CubicMetersPalletConverter(state, viewModel)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        PiecePalletConverter(state, viewModel)
    }
}














