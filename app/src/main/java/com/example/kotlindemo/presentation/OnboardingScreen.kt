package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotlindemo.viewmodel.onboarding.OnboardingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    viewModel.MainContent()
}
