package com.spear.coindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import  com.spear.coindemo.viewmodel.onboarding.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    viewModel.MainContent()
}
