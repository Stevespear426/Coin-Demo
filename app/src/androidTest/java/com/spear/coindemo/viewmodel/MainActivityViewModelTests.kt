package com.spear.coindemo.viewmodel

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.spear.coindemo.presentation.MainActivity
import com.spear.coindemo.repository.AppRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: MainActivityViewModel

    @MockK
    lateinit var mockAppRepository: AppRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MainActivityViewModel(mockAppRepository)
    }

    @Test
    fun mainContentNotOnboarded() {
        every { mockAppRepository.isOnboarded() } returns flow {
            emit(false)
        }
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val onboarding =
            composeTestRule.onNode(hasTestTag("Test Onboarding"), useUnmergedTree = true)
        onboarding.assertIsDisplayed()
    }

    @Test
    fun mainContentOnboarded() {
        every { mockAppRepository.isOnboarded() } returns flow {
            emit(true)
        }
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val navHost = composeTestRule.onNode(hasTestTag("Test NavHost"), useUnmergedTree = true)
        navHost.assertIsDisplayed()
    }
}