package com.commandiron.tools_presentation.weather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core.util.UiEvent
import com.commandiron.core_ui.LocalSpacing
import com.commandiron.core.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navigateUp:() -> Unit
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.NavigateUp -> { navigateUp() }
                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = spacing.defaultHorizontalScreenPadding,
                top = spacing.spaceMedium,
                end = spacing.defaultHorizontalScreenPadding,
                bottom = spacing.bottomNavigationHeight
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { viewModel.onEvent(WeatherUserEvent.BackClick) },
                text = "Geri Dön",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .alignBy(LastBaseline)
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.alignBy(LastBaseline),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "İstanbul, Türkiye",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Bugünün Raporu ",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault()).format(Date()),
            style = MaterialTheme.typography.titleSmall,
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.25f),
            painter = painterResource(id = R.drawable.partyly_cloudy),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Parçalı Bulutlu",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "°",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
                color = MaterialTheme.colorScheme.background
            )
            Text(
                text = "29",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
            )
            Text(
                text = "°",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 128.sp
                ),
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.padding(
                    vertical = spacing.spaceLarge,
                    horizontal = spacing.spaceMedium
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "64%",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = "Nem")
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(0.5f),
                    color = MaterialTheme.colorScheme.primary
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "11km",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = "Görüş")
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(0.5f),
                    color = MaterialTheme.colorScheme.primary
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "8km",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = "Rüzgar")
                }
            }
        }
    }
}