package com.commandiron.tools_presentation.rebarPricesTool

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.Strings.Turkish.REBAR_PRICES
import com.commandiron.tools_presentation.BackText
import com.commandiron.tools_presentation.rebarPricesTool.components.RebarPriceItem

@Composable
fun RebarPricesScreen(
    viewModel: RebarPricesViewModel = hiltViewModel(),
    navigateUp:() -> Unit
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
                UiEvent.NavigateUp -> {
                    navigateUp()
                }
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPadding)
    ) {
        BackText { viewModel.onEvent( RebarPricesUserEvent.BackTextClick ) }
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            text = REBAR_PRICES,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        LazyColumn {
            state.rebarPrices?.let {
                items(it){ item ->
                    RebarPriceItem(
                        date = item.date,
                        city = item.city,
                        q8mmPrice = item.q8mmPrice,
                        q10mmPrice = item.q10mmPrice,
                        q1232mmPrice = item.q1232mmPrice
                    )
                }
            }
        }
    }
}