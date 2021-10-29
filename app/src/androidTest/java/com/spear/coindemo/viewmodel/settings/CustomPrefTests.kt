package com.spear.coindemo.viewmodel.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.R
import com.spear.coindemo.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CustomPrefTests {

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
    fun testTextOnly() {
        composeTestRule.setContent {
            CustomPref("Title", onClick = mockk(relaxed = true))
        }

        val row = composeTestRule.onNode(hasTestTag("Test Row Title"), useUnmergedTree = true)
        row.assertIsDisplayed()

        val mainText = composeTestRule.onNode(hasTestTag("Test Title"), useUnmergedTree = true)
        mainText.assertIsDisplayed()
        mainText.assertTextEquals("Title")

        val icon =
            composeTestRule.onNode(hasTestTag("Test Icon"), useUnmergedTree = true)
        icon.assertDoesNotExist()
    }

    @Test
    fun testTextAndIcon() {
        composeTestRule.setContent {
            CustomPref("Title", R.drawable.github, mockk(relaxed = true))
        }

        val row = composeTestRule.onNode(hasTestTag("Test Row Title"), useUnmergedTree = true)
        row.assertIsDisplayed()

        val mainText = composeTestRule.onNode(hasTestTag("Test Title"), useUnmergedTree = true)
        mainText.assertIsDisplayed()
        mainText.assertTextEquals("Title")

        val icon =
            composeTestRule.onNode(hasTestTag("Test Icon"), useUnmergedTree = true)
        icon.assertIsDisplayed()
    }

    @Test
    fun testClick() {
        val mockClick = mockk<() -> Unit>(relaxed = true)
        composeTestRule.setContent {
            CustomPref("Title", onClick = mockClick)
        }

        val row = composeTestRule.onNode(hasTestTag("Test Row Title"), useUnmergedTree = true)
        row.assertIsDisplayed()
        row.performClick()

        verify { mockClick() }
    }
}