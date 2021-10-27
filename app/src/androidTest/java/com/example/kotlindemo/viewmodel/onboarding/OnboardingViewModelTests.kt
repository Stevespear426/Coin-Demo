package com.example.kotlindemo.viewmodel.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.AppRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
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
    }
}