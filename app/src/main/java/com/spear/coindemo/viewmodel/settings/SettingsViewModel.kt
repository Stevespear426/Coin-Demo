package com.spear.coindemo.viewmodel.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spear.coindemo.BuildConfig
import com.spear.coindemo.common.Constants.GITHUB_URL
import com.spear.coindemo.common.Constants.LINKEDIN_URL
import com.spear.coindemo.repository.AppRepository
import com.spear.coindemo.repository.use_case.settings.LaunchUrlUseCase
import com.spear.coindemo.ui.theme.LightBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.spear.coindemo.R

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val launchUrlUseCase: LaunchUrlUseCase
) : ViewModel() {

    @Composable
    fun MainContent() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .testTag("Test SettingsScreen MainContent")
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("App Settings", color = LightBlue)
            Spacer(modifier = Modifier.height(10.dp))
            CustomPref(
                title = "Re-Show Onboarding",
            ) {
                viewModelScope.launch { appRepository.setOnboarded(false) }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Developer Info", color = LightBlue)
            Spacer(modifier = Modifier.height(10.dp))
            CustomPref(
                title = "Github",
                icon = R.drawable.github,
            ) { launchUrlUseCase(context, GITHUB_URL) }

            Spacer(modifier = Modifier.height(10.dp))
            CustomPref(
                title = "LinkedIn",
                icon = R.drawable.linkedin,
            ) { launchUrlUseCase(context, LINKEDIN_URL) }

            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                "Version: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag("Test Version")
                    .padding(bottom = 65.dp)
            )
        }
    }
}
