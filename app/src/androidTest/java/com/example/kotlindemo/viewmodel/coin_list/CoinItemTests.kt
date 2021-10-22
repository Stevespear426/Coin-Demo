package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.Coin
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinItemTests {

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
            CoinItem(coin = getCoin(), mockk())
        }
        val mainText = composeTestRule.onNode(hasTestTag("Main Text"), useUnmergedTree = true)
        mainText.assertIsDisplayed()
        mainText.assertTextEquals("1. name (symbol)")
    }

    @Test
    fun testActiveText() {
        composeTestRule.setContent {
            CoinItem(coin = getCoin(), mockk())
        }
        val activeText = composeTestRule.onNode(hasTestTag("Active Text"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("Active")
    }

    @Test
    fun testInactiveText() {
        composeTestRule.setContent {
            CoinItem(coin = getCoin().copy(isActive = false), mockk())
        }
        val activeText = composeTestRule.onNode(hasTestTag("Active Text"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("Inactive")
    }

    @Test
    fun testOnClick() {
        val mockClick = mockk<(id: String) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            CoinItem(coin = getCoin(), mockClick)
        }
        val row = composeTestRule.onNode(hasTestTag("Row"), useUnmergedTree = true)
        row.assertIsDisplayed()
        row.performClick()
        verify { mockClick(eq("id1234")) }
    }

    private fun getCoin(): Coin = Coin(
        id = "id1234",
        isActive = true,
        isNew = true,
        name = "name",
        rank = 1,
        symbol = "symbol",
        type = "type",
    )
}