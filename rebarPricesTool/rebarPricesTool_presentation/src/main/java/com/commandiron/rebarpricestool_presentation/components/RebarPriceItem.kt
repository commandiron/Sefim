package com.commandiron.rebarpricestool_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.Strings.EMPTY_RESULT_DOUBLE_DASH
import com.commandiron.core_ui.util.Strings.PHI_10
import com.commandiron.core_ui.util.Strings.PHI_12_DASH_32
import com.commandiron.core_ui.util.Strings.PHI_8
import com.commandiron.rebarpricestool_domain.model.RebarPrice
import java.time.format.DateTimeFormatter

@Composable
fun RebarPriceItem(
    rebarPrice: RebarPrice,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceSmall),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium)
        ) {
            Row() {
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = rebarPrice.city,
                    style = textStyle.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = rebarPrice.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Divider(color = LocalContentColor.current.copy(alpha = 0.1f))
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
                        style = textStyle
                    )
                    Text(
                        modifier = Modifier,
                        text = rebarPrice.q8mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = textStyle
                    )

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = PHI_10,
                        style = textStyle
                    )
                    Text(
                        text = rebarPrice.q10mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = textStyle
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = PHI_12_DASH_32,
                        style = textStyle
                    )
                    Text(
                        text = rebarPrice.q1232mmPrice.ifEmpty { EMPTY_RESULT_DOUBLE_DASH },
                        style = textStyle
                    )
                }
            }
        }
    }
}