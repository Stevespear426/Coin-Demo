package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.MainActivity
import com.spear.coindemo.repository.model.ChangeDuration
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DurationRowTests {

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
    fun testDurationRow() {
        composeTestRule.setContent {
            DurationRow(ChangeDuration.HOURS_1, mockk())
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val h1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1H"),
            useUnmergedTree = true
        )
        h1.assertIsDisplayed()

        val d1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1D"),
            useUnmergedTree = true
        )
        d1.assertIsDisplayed()

        val w1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1W"),
            useUnmergedTree = true
        )
        w1.assertIsDisplayed()

        val m1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1M"),
            useUnmergedTree = true
        )
        m1.assertIsDisplayed()

        val y1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1Y"),
            useUnmergedTree = true
        )
        y1.assertIsDisplayed()

    }

    @Test
    fun testClickHour() {
        val mockClick = mockk<(changeDuration: ChangeDuration) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationRow(ChangeDuration.DAY, mockClick)
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val h1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1H"),
            useUnmergedTree = true
        )
        h1.assertIsDisplayed()
        h1.performClick()

        verify { mockClick(ChangeDuration.HOURS_1) }
    }

    @Test
    fun testClickDay() {
        val mockClick = mockk<(changeDuration: ChangeDuration) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationRow(ChangeDuration.DAY, mockClick)
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val d1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1D"),
            useUnmergedTree = true
        )
        d1.assertIsDisplayed()
        d1.performClick()

        verify { mockClick(ChangeDuration.DAY) }
    }

    @Test
    fun testClickWeek() {
        val mockClick = mockk<(changeDuration: ChangeDuration) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationRow(ChangeDuration.DAY, mockClick)
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val w1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1W"),
            useUnmergedTree = true
        )
        w1.assertIsDisplayed()
        w1.performClick()

        verify { mockClick(ChangeDuration.WEEK) }
    }

    @Test
    fun testClickMonth() {
        val mockClick = mockk<(changeDuration: ChangeDuration) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationRow(ChangeDuration.DAY, mockClick)
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val m1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1M"),
            useUnmergedTree = true
        )
        m1.assertIsDisplayed()
        m1.performClick()

        verify { mockClick(ChangeDuration.MONTH) }
    }

    @Test
    fun testClickYear() {
        val mockClick = mockk<(changeDuration: ChangeDuration) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            DurationRow(ChangeDuration.DAY, mockClick)
        }
        val main = composeTestRule.onNode(hasTestTag("Test DurationRow"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val y1 = composeTestRule.onNode(
            hasTestTag("Test DurationItem Button 1Y"),
            useUnmergedTree = true
        )
        y1.assertIsDisplayed()
        y1.performClick()

        verify { mockClick(ChangeDuration.YEAR) }
    }
}