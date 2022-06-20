package com.commandiron.sefim.navigation

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.sefim.core.NoRippleTheme
import com.commandiron.sefim.presentation.home.HomeScreen
import com.commandiron.sefim.ui.theme.SefimTheme

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navigationItems: List<NavigationItem>
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            navigationItems.forEach { item ->
                var isSelected by remember { mutableStateOf(false)}
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        isSelected = !isSelected
                    },
                    icon = {
                        Icon(
                            imageVector = if(isSelected) {
                                item.selectedImageVector
                            } else item.unSelectedImageVector,
                            contentDescription = item.title,
                            tint = if(isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    alwaysShowLabel = isSelected
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigation() {
    SefimTheme() {
        BottomNavigation(navigationItems = defaultNavigationItems)
    }
}