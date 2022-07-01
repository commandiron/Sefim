package com.commandiron.tools_presentation.rebarPricesTool.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import com.commandiron.core_ui.LocalSpacing

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
            .fillMaxWidth()
            .padding(spacing.spaceSmall),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceSmall),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall)
        ) {
            Row() {
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = city,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Row {
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = "Φ8: ",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    modifier = Modifier.alignBy(LastBaseline),
                    text = "$q8mmPrice TL",
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            Text(
                text = "Φ10: $q10mmPrice TL",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Φ12-32: $q1232mmPrice TL",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}