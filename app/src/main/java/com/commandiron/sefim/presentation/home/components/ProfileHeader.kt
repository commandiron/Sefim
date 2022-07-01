package com.commandiron.sefim.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.LocalSpacing

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    height: Dp = 48.dp,
    imageUrl: String?,
    onEditClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .height(height),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                modifier = Modifier.aspectRatio(1f),
                imageUrl = imageUrl,
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hoşgeldiniz",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LocalContentColor.current.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Icon(
                        modifier = Modifier.height(height / 4),
                        imageVector = Icons.Default.WavingHand,
                        contentDescription = null,
                        tint = Color(0xFFF8C035)
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                Text(
                    text = "Şefim",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { onEditClick() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(
                onClick = { onNotificationClick() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}