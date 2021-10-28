package com.spear.coindemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun SettingsScreen(
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .testTag("Test SettingsScreen MainContent")) {
        Text("Settings", Modifier.align(Alignment.Center))
    }
}