package com.commandiron.tools_presentation.rebarPricesTool.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core_ui.Strings.EMPTY_RESULT_DOUBLE_DASH
import com.commandiron.core_ui.Strings.PHI_10
import com.commandiron.core_ui.Strings.PHI_12_DASH_32
import com.commandiron.core_ui.Strings.PHI_8

@Composable
fun RebarPriceItem(
    date: String,
    city: String,
    q8mmPrice: String,
    q10mmPrice: String,
    q1232mmPrice: String,
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceSmall),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium)
        ) {
            Row() {
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = city,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Divider(color = MaterialTheme.colorScheme.onTertiaryContainer)
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = PHI_8,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier,
                        text = q8mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = MaterialTheme.typography.titleSmall
                    )

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = PHI_10,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = q10mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = PHI_12_DASH_32,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = q1232mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}