package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.*
import com.commandiron.sefim.presentation.home.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
    showSnackbar: (String) -> Unit
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> navigateTo(event.route)
                is UiEvent.ShowSnackbar -> showSnackbar(event.message)
                else -> {}
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null) {
                viewModel.onEvent(HomeUserEvent.SpaceClick)
            }
    ) {
        if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
            HomeCompactContent(viewModel)
        }else{
            HomeExpandedContent(viewModel)
        }
    }

}