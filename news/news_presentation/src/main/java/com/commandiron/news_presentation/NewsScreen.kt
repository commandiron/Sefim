package com.commandiron.news_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Haberler - Yenilikler")
    }
}