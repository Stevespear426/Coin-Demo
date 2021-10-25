package com.example.kotlindemo.viewmodel.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.kotlindemo.R
import com.example.kotlindemo.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OnboardingItemTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun testMainText() {
        composeTestRule.setContent {
            OnboardingItem(getItem())
        }
        val icon = composeTestRule.onNode(hasTestTag("Icon"), useUnmergedTree = true)
        icon.assertIsDisplayed()

        val titleText = composeTestRule.onNode(hasTestTag("Title"), useUnmergedTree = true)
        titleText.assertIsDisplayed()
        titleText.assertTextEquals("Welcome to my Demo App!")

        val bodyText = composeTestRule.onNode(hasTestTag("Body"), useUnmergedTree = true)
        bodyText.assertIsDisplayed()
        bodyText.assertTextEquals("In this app I experiment with new versions of Android tools, UI components, 3rd party libraries, etc…")
    }


    private fun getItem(): OnboardingItemData = OnboardingItemData(
        R.string.onboarding_title_1,
        R.string.onboarding_body_1,
        R.drawable.engineering
    )
}