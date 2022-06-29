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
import com.commandiron.tools_presentation.aeratedConcTool.components.PieceToPalletComponent

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
            text = "Gazbeton Palet Hesaplayıcı",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        PieceToPalletComponent(
            title = state.piecePalletLabel
                    + " - "
                    + state.resultSymbol,
            onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.ChangeUnitClick) },
            firstValue = state.piecePallet,
            firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.PieceChange(it)) },
            firstValueLabel = state.piecePalletLabel,
            firstValueSymbol = state.piecePalletSymbol,
            firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.PieceKeyboardDone) },
            secondValue = state.thickness,
            secondValueChange = {},
            secondValueLabel = state.thicknessLabel,
            secondValueSymbol = state.thicknessSymbol,
            secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.ThicknessClick) },
            dropDownIsExpanded = state.thicknessDropDownIsExpanded,
            dropDownItems = state.thicknessList,
            onDropDownItemSelect = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownSelect(it))
            },
            onDropDownDismissRequest = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
            },
            resultText = state.result,
            resultSymbol = state.resultSymbol
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        PieceToPalletComponent(
            title = state.piecePalletLabel
                    + " - "
                    + state.resultSymbol,
            onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.ChangeUnitClick) },
            firstValue = state.piecePallet,
            firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.PieceChange(it)) },
            firstValueLabel = state.piecePalletLabel,
            firstValueSymbol = state.piecePalletSymbol,
            firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.PieceKeyboardDone) },
            secondValue = state.thickness,
            secondValueChange = {},
            secondValueLabel = state.thicknessLabel,
            secondValueSymbol = state.thicknessSymbol,
            secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.ThicknessClick) },
            dropDownIsExpanded = state.thicknessDropDownIsExpanded,
            dropDownItems = state.thicknessList,
            onDropDownItemSelect = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownSelect(it))
            },
            onDropDownDismissRequest = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
            },
            resultText = state.result,
            resultSymbol = state.resultSymbol
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        PieceToPalletComponent(
            title = state.piecePalletLabel
                    + " - "
                    + state.resultSymbol,
            onChangeUnitClick = { viewModel.onEvent(AeratedConcToolUserEvent.ChangeUnitClick) },
            firstValue = state.piecePallet,
            firstValueChange = { viewModel.onEvent(AeratedConcToolUserEvent.PieceChange(it)) },
            firstValueLabel = state.piecePalletLabel,
            firstValueSymbol = state.piecePalletSymbol,
            firstValueOnDone = { viewModel.onEvent(AeratedConcToolUserEvent.PieceKeyboardDone) },
            secondValue = state.thickness,
            secondValueChange = {},
            secondValueLabel = state.thicknessLabel,
            secondValueSymbol = state.thicknessSymbol,
            secondValueOnClick = { viewModel.onEvent(AeratedConcToolUserEvent.ThicknessClick) },
            dropDownIsExpanded = state.thicknessDropDownIsExpanded,
            dropDownItems = state.thicknessList,
            onDropDownItemSelect = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownSelect(it))
            },
            onDropDownDismissRequest = {
                viewModel.onEvent(AeratedConcToolUserEvent.ThicknessDropDownDismissClick)
            },
            resultText = state.result,
            resultSymbol = state.resultSymbol
        )
    }
}














