package com.commandiron.rebarpricestool_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.Strings.Turkish.REBAR_PRICES
import com.commandiron.core_ui.components.ToolHeader
import com.commandiron.core_ui.util.*
import com.commandiron.rebarpricestool_presentation.components.RebarPriceItem

@Composable
fun RebarPricesScreen(
    viewModel: RebarPricesViewModel = hiltViewModel(),
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
fun CompactWindowTypeContent(viewModel: RebarPricesViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact)
    ) {
        ToolHeader(
            title = REBAR_PRICES,
            onIconClick = {viewModel.onEvent(RebarPricesUserEvent.Back)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            state.rebarPrices?.let {
                items(it){ item ->
                    RebarPriceItem(
                        rebarPrice = item
                    )
                }
            }
        }
    }
}
@Composable
fun ExpandedWindowTypeContent(viewModel: RebarPricesViewModel) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForExpandedNoBottomNav)
    ) {
        ToolHeader(
            title = REBAR_PRICES,
            onIconClick = {viewModel.onEvent(RebarPricesUserEvent.Back)}
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ){
            state.rebarPrices?.let {
                items(it){ item ->
                    RebarPriceItem(
                        rebarPrice = item
                    )
                }
            }
        }
    }
}