package com.example.kotlindemo.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.presentation.OnboardingScreen
import com.example.kotlindemo.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val appRepository: AppRepository
) : ViewModel() {

    @Composable
    fun MainContent() {
        val onboardState = appRepository.isOnboarded().collectAsState(initial = null)
        onboardState.value?.let { onboarded ->
            if (!onboarded) OnboardingScreen()
            else MainScaffold()
        }
    }
}
