package com.spear.coindemo.viewmodel.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OnboardingTests {

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
    fun testNavigation() {
        val mockClick = mockk<() -> Unit>(relaxed = true)
        composeTestRule.setContent {
            Onboarding(mockClick)
        }

        val indicator1 =
            composeTestRule.onNode(hasTestTag("Indicator_0_true"), useUnmergedTree = true)
        indicator1.assertIsDisplayed()

        val fab = composeTestRule.onNode(hasTestTag("FAB"), useUnmergedTree = true)
        fab.assertIsDisplayed()
        fab.performClick()

        verify { mockClick wasNot Called }

        val indicator2 =
            composeTestRule.onNode(hasTestTag("Indicator_1_true"), useUnmergedTree = true)
        indicator2.assertIsDisplayed()

        fab.performClick()
        verify { mockClick wasNot Called }

        val indicator3 =
            composeTestRule.onNode(hasTestTag("Indicator_2_true"), useUnmergedTree = true)
        indicator3.assertIsDisplayed()

        fab.performClick()
        verify { mockClick() }
    }

}