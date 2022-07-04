package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.Strings.Turkish.INNOVATIONS
import com.commandiron.core_ui.util.Strings.Turkish.MY_FAVORITE_TOOLS
import com.commandiron.core_ui.util.Strings.Turkish.NEWS
import com.commandiron.core_ui.util.Strings.Turkish.RECOMMENDED_TOOLS
import com.commandiron.core_ui.components.carousel.Carousel
import com.commandiron.core_ui.components.carousel.CarouselDefaults
import com.commandiron.core_ui.util.*
import com.commandiron.sefim.presentation.home.components.*
import com.commandiron.tools_presentation.components.tool_items.ToolsVerticalGrid

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
    showSnackbar: (String) -> Unit
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    val systemUiController = LocalSystemUiController.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> navigateTo(event.route)
                is UiEvent.ShowSnackbar -> showSnackbar(event.message)
                else -> {}
            }
        }
    }
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.onBackground
    )
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
            CompactWindowTypeContent(viewModel)
        }else{
            ExpandedWindowTypeContent(viewModel)
        }
    }

}