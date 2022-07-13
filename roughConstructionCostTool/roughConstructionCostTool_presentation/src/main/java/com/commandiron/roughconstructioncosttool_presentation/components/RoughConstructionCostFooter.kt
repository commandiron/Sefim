package com.commandiron.roughconstructioncosttool_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.commandiron.core_ui.components.getThousandSeparatorTransformedText
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings
import com.commandiron.core_ui.util.Strings.TURKISH_LIRA

@Composable
fun RoughConstructionCostFooter(
    resultText: String
) {
    val spacing = LocalSpacing.current
    Column {
        Text(
            text = Strings.Turkish.GRAND_TOTAL,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Row(
            modifier = Modifier
                .height(spacing.spaceExtraLarge)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = MaterialTheme.shapes.large
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getThousandSeparatorTransformedText(text = resultText, addedUnit = TURKISH_LIRA),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}