package com.spear.coindemo.viewmodel.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.BuildConfig
import com.spear.coindemo.common.Constants.GITHUB_URL
import com.spear.coindemo.common.Constants.LINKEDIN_URL
import com.spear.coindemo.MainActivity
import com.spear.coindemo.repository.AppRepository
import com.spear.coindemo.repository.use_case.settings.LaunchUrlUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SettingsViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: SettingsViewModel

    @RelaxedMockK
    lateinit var mockAppRepository: AppRepository

    @RelaxedMockK
    lateinit var mockLaunchUrlUseCase: LaunchUrlUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = SettingsViewModel(mockAppRepository, mockLaunchUrlUseCase)
    }

    @Test
    fun testMainContent() {
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val settings =
            composeTestRule.onNode(
                hasTestTag("Test SettingsScreen MainContent"),
                useUnmergedTree = true
            )
        settings.assertIsDisplayed()

        val onboarding =
            composeTestRule.onNode(
                hasTestTag("Test Row Re-Show Onboarding"),
                useUnmergedTree = true
            )
        onboarding.assertIsDisplayed()

        val github =
            composeTestRule.onNode(
                hasTestTag("Test Row Github"),
                useUnmergedTree = true
            )
        github.assertIsDisplayed()

        val linkedin =
            composeTestRule.onNode(
                hasTestTag("Test Row LinkedIn"),
                useUnmergedTree = true
            )
        linkedin.assertIsDisplayed()

        val version =
            composeTestRule.onNode(
                hasTestTag("Test Version"),
                useUnmergedTree = true
            )
        version.assertIsDisplayed()
        version.assertTextEquals("Version: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
    }

    @Test
    fun testClickOnboarding() {
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val onboarding =
            composeTestRule.onNode(
                hasTestTag("Test Row Re-Show Onboarding"),
                useUnmergedTree = true
            )
        onboarding.assertIsDisplayed()
        onboarding.performClick()
        coVerify { mockAppRepository.setOnboarded(false) }
    }

    @Test
    fun testClickGithub() {
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val github =
            composeTestRule.onNode(
                hasTestTag("Test Row Github"),
                useUnmergedTree = true
            )
        github.assertIsDisplayed()
        github.performClick()

        verify { mockLaunchUrlUseCase(any(), GITHUB_URL) }
    }

    @Test
    fun testClickLinkedIn() {
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val linkedin =
            composeTestRule.onNode(
                hasTestTag("Test Row LinkedIn"),
                useUnmergedTree = true
            )
        linkedin.assertIsDisplayed()
        linkedin.performClick()

        verify { mockLaunchUrlUseCase(any(), LINKEDIN_URL) }
    }
}