package com.example.kotlindemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun FavoritesScreen(
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .testTag("Test FavoritesScreen MainContent")) {
        Text("Favorites", Modifier.align(Alignment.Center))
    }
}
