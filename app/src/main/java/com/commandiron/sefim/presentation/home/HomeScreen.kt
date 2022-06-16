package com.commandiron.sefim.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.commandiron.AppBar

@Composable
fun HomeScreen() {
    Column() {
        AppBar(
            onMenuIconClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}