package com.commandiron.news_presentation

import androidx.compose.foundation.layout.*
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
import com.commandiron.core_ui.util.LocalSpacing

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
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
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        AppLogoWithName(
            modifier = Modifier.fillMaxWidth(0.15f),
            alpha = 0.2f
        )
        Spacer(modifier = Modifier.height(spacing.spaceXXLarge))
    }
}