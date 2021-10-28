package com.spear.coindemo.viewmodel.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.spear.coindemo.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BottomSectionTests {

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
    fun testIndex1() {
        composeTestRule.setContent {
            BottomSection(size = 3, index = 0) {}
        }
        val icon = composeTestRule.onNode(hasTestTag("KeyboardArrowRight"), useUnmergedTree = true)
        icon.assertIsDisplayed()

        val indicator1 =
            composeTestRule.onNode(hasTestTag("Indicator_0_true"), useUnmergedTree = true)
        indicator1.assertIsDisplayed()

        val indicator2 =
            composeTestRule.onNode(hasTestTag("Indicator_1_false"), useUnmergedTree = true)
        indicator2.assertIsDisplayed()

        val indicator3 =
            composeTestRule.onNode(hasTestTag("Indicator_2_false"), useUnmergedTree = true)
        indicator3.assertIsDisplayed()
    }

    @Test
    fun testIndex2() {
        composeTestRule.setContent {
            BottomSection(size = 3, index = 1) {}
        }
        val icon = composeTestRule.onNode(hasTestTag("KeyboardArrowRight"), useUnmergedTree = true)
        icon.assertIsDisplayed()

        val indicator1 =
            composeTestRule.onNode(hasTestTag("Indicator_0_false"), useUnmergedTree = true)
        indicator1.assertIsDisplayed()

        val indicator2 =
            composeTestRule.onNode(hasTestTag("Indicator_1_true"), useUnmergedTree = true)
        indicator2.assertIsDisplayed()

        val indicator3 =
            composeTestRule.onNode(hasTestTag("Indicator_2_false"), useUnmergedTree = true)
        indicator3.assertIsDisplayed()
    }

    @Test
    fun testIndex3() {
        composeTestRule.setContent {
            BottomSection(size = 3, index = 2) {}
        }
        val icon = composeTestRule.onNode(hasTestTag("Done"), useUnmergedTree = true)
        icon.assertIsDisplayed()

        val indicator1 =
            composeTestRule.onNode(hasTestTag("Indicator_0_false"), useUnmergedTree = true)
        indicator1.assertIsDisplayed()

        val indicator2 =
            composeTestRule.onNode(hasTestTag("Indicator_1_false"), useUnmergedTree = true)
        indicator2.assertIsDisplayed()

        val indicator3 =
            composeTestRule.onNode(hasTestTag("Indicator_2_true"), useUnmergedTree = true)
        indicator3.assertIsDisplayed()
    }
}