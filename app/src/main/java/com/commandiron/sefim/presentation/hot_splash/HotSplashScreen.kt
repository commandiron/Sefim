package com.commandiron.sefim.presentation.hot_splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.core_ui.components.AppLogoWithName
import com.commandiron.core_ui.util.LocalSpacing
import com.commandiron.core_ui.util.LocalSystemUiController
import com.commandiron.core_ui.R
import com.commandiron.core_ui.theme.MyPrimaryColor
import com.commandiron.core_ui.theme.companyColor
import kotlinx.coroutines.delay

@Composable
fun HotSplashScreen(
    viewModel: HotSplashViewModel = hiltViewModel(),
    onFinish:() -> Unit
) {
    val spacing = LocalSpacing.current
    val systemUiController = LocalSystemUiController.current
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.background
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.background
    )
    LaunchedEffect(key1 = true){
        delay(3000)
        onFinish()
    }
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppLogoWithName(
                modifier = Modifier.fillMaxWidth(0.2f),
                color = MyPrimaryColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceExtraLarge),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "from",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = LocalContentColor.current.copy(
                        alpha = 0.5f
                    )
                )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.height(20.dp),
                        painter = painterResource(id = R.drawable.ci_tech_logo_4),
                        contentDescription = "CITECH",
                        tint = companyColor
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Text(
                        text = "CITECH",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = companyColor
                    )
                }
            }
        }
    }
}