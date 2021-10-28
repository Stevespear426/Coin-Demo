package com.spear.coindemo.viewmodel.onboarding

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spear.coindemo.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    @Composable
    fun MainContent() {
        Onboarding {
            viewModelScope.launch { appRepository.setOnboarded() }
        }
    }
}
