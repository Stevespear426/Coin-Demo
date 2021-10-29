package com.spear.coindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.spear.coindemo.viewmodel.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    viewModel.MainContent()
}
