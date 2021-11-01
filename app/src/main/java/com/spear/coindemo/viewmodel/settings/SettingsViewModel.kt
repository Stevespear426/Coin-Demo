package com.spear.coindemo.viewmodel.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spear.coindemo.BuildConfig
import com.spear.coindemo.R
import com.spear.coindemo.common.Constants.GITHUB_URL
import com.spear.coindemo.common.Constants.LINKEDIN_URL
import com.spear.coindemo.repository.AppRepository
import com.spear.coindemo.repository.use_case.settings.LaunchUrlUseCase
import com.spear.coindemo.ui.theme.LightBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val launchUrlUseCase: LaunchUrlUseCase
) : ViewModel() {

    companion object {
        const val LARGE_SPACE = 30
        const val SMALL_SPACE = 20
    }

    @Composable
    fun MainContent() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .testTag("Test SettingsScreen MainContent")
        ) {
            Spacer(modifier = Modifier.height(SMALL_SPACE.dp))
            Text(stringResource(R.string.app_settings), color = LightBlue)
            Spacer(modifier = Modifier.height(SMALL_SPACE.dp))
            CustomPref(
                title = stringResource(R.string.reshow_onboarding),
            ) {
                viewModelScope.launch { appRepository.setOnboarded(false) }
            }

            Spacer(modifier = Modifier.height(LARGE_SPACE.dp))
            Text(stringResource(R.string.developer_info), color = LightBlue)
            Spacer(modifier = Modifier.height(SMALL_SPACE.dp))
            CustomPref(
                title = stringResource(R.string.github),
                icon = R.drawable.github,
            ) { launchUrlUseCase(context, GITHUB_URL) }

            Spacer(modifier = Modifier.height(SMALL_SPACE.dp))
            CustomPref(
                title = stringResource(R.string.linkedin),
                icon = R.drawable.linkedin,
            ) { launchUrlUseCase(context, LINKEDIN_URL) }

            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                stringResource(
                    R.string.version,
                    BuildConfig.VERSION_NAME,
                    BuildConfig.VERSION_CODE
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag("Test Version")
                    .padding(bottom = 65.dp)
            )
        }
    }
}
