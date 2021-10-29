package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.MainActivity
import com.spear.coindemo.repository.model.ChangeDuration
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.Quotes
import com.spear.coindemo.repository.model.USD
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
            CoinItem(coin = getCoin(), ChangeDuration.HOURS_1, mockk())
        }
        val mainText = composeTestRule.onNode(hasTestTag("Main Text"), useUnmergedTree = true)
        mainText.assertIsDisplayed()
        mainText.assertTextEquals("name (symbol)")
    }

    @Test
    fun testPriceText() {
        composeTestRule.setContent {
            CoinItem(
                coin = getCoin().copy(quotes = Quotes(USD(price = 1.00))),
                ChangeDuration.HOURS_1,
                mockk()
            )
        }
        val activeText = composeTestRule.onNode(hasTestTag("Test Price"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("$1.00")
    }

    @Test
    fun testPriceAndDiffText() {
        composeTestRule.setContent {
            CoinItem(
                coin = getCoin().copy(quotes = Quotes(USD(price = 1.00, change1h = -0.15))),
                ChangeDuration.HOURS_1,
                mockk()
            )
        }
        val activeText = composeTestRule.onNode(hasTestTag("Test Price"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("$1.00 (-0.15%)")
    }

    @Test
    fun testNoPriceText() {
        composeTestRule.setContent {
            CoinItem(coin = getCoin(), ChangeDuration.HOURS_1, mockk())
        }
        val activeText = composeTestRule.onNode(hasTestTag("Test Price"), useUnmergedTree = true)
        activeText.assertDoesNotExist()
    }

    @Test
    fun testPriceUnavailableText() {
        composeTestRule.setContent {
            CoinItem(coin = getCoin().copy(quotes = Quotes(USD())), ChangeDuration.HOURS_1, mockk())
        }
        val activeText = composeTestRule.onNode(hasTestTag("Test Price"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("Unavailable")
    }

    @Test
    fun testOnClick() {
        val mockClick = mockk<(id: String) -> Unit>(relaxed = true)
        composeTestRule.setContent {
            CoinItem(coin = getCoin(), ChangeDuration.HOURS_1, mockClick)
        }
        val row = composeTestRule.onNode(hasTestTag("Test name"), useUnmergedTree = true)
        row.assertIsDisplayed()
        row.performClick()
        verify { mockClick(eq("id1234")) }
    }

    private fun getCoin(): Coin = Coin(
        id = "id1234",
        name = "name",
        rank = 1,
        symbol = "symbol",
    )
}