package com.spear.coindemo.viewmodel.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.presentation.MainActivity
import com.spear.coindemo.repository.AppRepository
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
class OnboardingViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: OnboardingViewModel

    @RelaxedMockK
    lateinit var mockAppRepository: AppRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = OnboardingViewModel(mockAppRepository)
    }

    @Test
    fun testCompletedClick() {
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val onboarding = composeTestRule.onNode(hasTestTag("Test Onboarding"), useUnmergedTree = true)
        onboarding.assertIsDisplayed()

        val fab = composeTestRule.onNode(hasTestTag("FAB"), useUnmergedTree = true)
        fab.assertIsDisplayed()
        fab.performClick()
        fab.performClick()
        fab.performClick()
        coVerify { mockAppRepository.setOnboarded() }
    }
}