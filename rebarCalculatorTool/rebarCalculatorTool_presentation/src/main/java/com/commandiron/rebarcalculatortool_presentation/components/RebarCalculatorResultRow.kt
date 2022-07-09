package com.commandiron.rebarcalculatortool_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings
import com.commandiron.rebarcalculatortool_presentation.RebarCalculatorViewModel

@Composable
fun RebarCalculatorResultRow(
    viewModel: RebarCalculatorViewModel
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(spacing.spaceExtraLarge)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${Strings.Turkish.GRAND_TOTAL} : ",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = state.grandResult,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        if(state.grandResult.isNotEmpty()){
            Text(
                text = state.grandResultUnit,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Text(
            text = state.grandResult2,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        if(state.grandResult2.isNotEmpty()){
            Text(
                text = state.grandResult2Unit,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}