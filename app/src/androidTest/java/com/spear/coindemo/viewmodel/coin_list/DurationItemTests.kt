package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
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
class DurationItemTests {

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
    fun testDurationItem() {
        composeTestRule.setContent {
            DurationItem(Modifier.wrapContentWidth(), "1H", false, mockk())
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationItem"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val button = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1H"),
            useUnmergedTree = true
        )
        button.assertIsDisplayed()

        val text =
            composeTestRule.onNode(hasTestTag("Test DurationItem Text"), useUnmergedTree = true)
        text.assertIsDisplayed()
        text.assertTextEquals("1H")
    }

    @Test
    fun testDurationItemClick() {
        val mockClick = mockk<() -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationItem(Modifier.wrapContentWidth(), "1H", false, mockClick)
        }

        val button = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1H"),
            useUnmergedTree = true
        )
        button.assertIsDisplayed()
        button.performClick()

        verify { mockClick() }
    }
}