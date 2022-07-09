package com.commandiron.aeratedconctool_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.aeratedconctool_presentation.components.converters.CubicMetersPalletConverter
import com.commandiron.aeratedconctool_presentation.components.converters.PiecePalletConverter
import com.commandiron.aeratedconctool_presentation.components.converters.SquareCubicConverter
import com.commandiron.aeratedconctool_presentation.components.converters.SquareMetersPalletConverter
import com.commandiron.core_ui.components.ToolHeader
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.core_ui.util.Strings.Turkish.AERATED_CONCRETE_CALCULATOR
import com.commandiron.core_ui.util.WindowInfo

@Composable
fun AeratedConcToolScreen(
    viewModel: AeratedConcToolViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.HideKeyboard -> {
                    keyboardController?.hide()
                }
                UiEvent.NavigateUp -> {
                    navigateUp()
                }
                else -> {}
            }
        }
    }
    if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        CompactWindowTypeContent(viewModel)
    }else{
        ExpandedWindowTypeContent(viewModel)
    }
}
@Composable
fun CompactWindowTypeContent(viewModel: AeratedConcToolViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact)
    ) {
        ToolHeader(
            title = AERATED_CONCRETE_CALCULATOR,
            onIconClick = {viewModel.onEvent(AeratedConcToolUserEvent.Back)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        SquareMetersPalletConverter(state, viewModel)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        CubicMetersPalletConverter(state, viewModel)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        PiecePalletConverter(state, viewModel)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SquareCubicConverter(state,viewModel)
    }
}
@Composable
fun ExpandedWindowTypeContent(viewModel: AeratedConcToolViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.defaultScreenPaddingForExpandedNoBottomNav)
    ) {
        ToolHeader(
            title = AERATED_CONCRETE_CALCULATOR,
            onIconClick = {viewModel.onEvent(AeratedConcToolUserEvent.Back)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn {
            item {
                SquareMetersPalletConverter(state, viewModel)
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                CubicMetersPalletConverter(state, viewModel)
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                PiecePalletConverter(state, viewModel)
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                SquareCubicConverter(state,viewModel)
            }
        }
    }
}