package com.commandiron.sefim.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.commandiron.core_ui.theme.NoRippleTheme

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onBottomNavItemClick:(String) -> Unit
) {
    val navigationItems = listOf(
        NavigationItem.HomeScreen,
        NavigationItem.Tools,
        NavigationItem.News
    )
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val bottomNavLineOffsetXAnim = remember { Animatable(0f) }
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        AnimatedVisibility(
            visible = navigationItems.map { it.route }.contains(currentRoute),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box {
                NavigationBar(
                    modifier = modifier.height(64.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
                    navigationItems.forEachIndexed { index, item ->
                        LaunchedEffect(key1 = currentRoute){
                            if(currentRoute == item.route){
                                bottomNavLineOffsetXAnim.animateTo(
                                    targetValue = screenWidth.value / navigationItems.size * index,
                                    animationSpec = tween(durationMillis = 700)
                                )
                            }
                        }
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = { onBottomNavItemClick(item.route) },
                            icon = {
                                Icon(
                                    painter = if(currentRoute == item.route) {
                                        painterResource(id = item.selectedImageResource!!)
                                    } else painterResource(id = item.unSelectedImageResource!!),
                                    contentDescription = null,
                                    tint = if(currentRoute == item.route) {
                                        if(currentRoute == NavigationItem.Tools.route){
                                            Color.Unspecified
                                        }else MaterialTheme.colorScheme.primary
                                    } else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
                                )
                            },
                            label = {
                                Text(
                                    text = item.title ?: "",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            },
                            alwaysShowLabel = false,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.background,
                            )
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(1f / navigationItems.size)
                        .offset(x = Dp(bottomNavLineOffsetXAnim.value)),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}