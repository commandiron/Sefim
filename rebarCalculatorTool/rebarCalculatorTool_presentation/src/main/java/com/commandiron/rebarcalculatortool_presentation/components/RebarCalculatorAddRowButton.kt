package com.commandiron.rebarcalculatortool_presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.rebarcalculatortool_presentation.RebarCalculatorUserEvent
import com.commandiron.rebarcalculatortool_presentation.RebarCalculatorViewModel

@Composable
fun RebarCalculatorAddRowButton(viewModel: RebarCalculatorViewModel) {
    val spacing = LocalSpacing.current
    IconButton(
        onClick = {
            viewModel.onEvent(RebarCalculatorUserEvent.AddRebarCalculatorItem)
        }
    ) {
        Icon(
            modifier = androidx.compose.ui.Modifier.size(spacing.spaceExtraLarge),
            imageVector = Icons.Default.AddCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}