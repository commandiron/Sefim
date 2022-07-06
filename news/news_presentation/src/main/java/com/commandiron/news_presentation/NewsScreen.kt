package com.commandiron.news_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.util.Strings.Turkish.SOON
import com.commandiron.core_ui.util.Strings.Turkish.WITH_YOU
import com.commandiron.core_ui.components.AppLogoWithName
import com.commandiron.core_ui.components.ToolHeader
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.LocalWindowTypeInfo
import com.commandiron.core_ui.util.Strings
import com.commandiron.core_ui.util.WindowInfo

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val windowTypeInfo = LocalWindowTypeInfo.current
    if(windowTypeInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        CompactWindowTypeContent(viewModel)
    }else{
        ExpandedWindowTypeContent(viewModel)
    }
}

@Composable
fun CompactWindowTypeContent(viewModel: NewsViewModel) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = SOON,
            style = MaterialTheme.typography.displayMedium,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = WITH_YOU,
            style = MaterialTheme.typography.displayMedium,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        AppLogoWithName(
            modifier = Modifier.fillMaxWidth(0.20f),
            alpha = 0.2f
        )
        Spacer(modifier = Modifier.height(spacing.spaceXXLarge))
    }
}
@Composable
fun ExpandedWindowTypeContent(viewModel: NewsViewModel) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.defaultScreenPaddingForCompact),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = SOON,
            style = MaterialTheme.typography.displayMedium,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = WITH_YOU,
            style = MaterialTheme.typography.displayMedium,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        AppLogoWithName(
            modifier = Modifier.fillMaxWidth(0.10f),
            alpha = 0.2f
        )
        Spacer(modifier = Modifier.height(spacing.spaceXXLarge))
    }
}