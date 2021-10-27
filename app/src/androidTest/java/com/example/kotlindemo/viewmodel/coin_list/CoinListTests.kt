package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.Coin
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinListTests {

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
    fun testCoinListNoFilter() {
        val list = getCoinList()
        composeTestRule.setContent {
            CoinList(list, mockk())
        }
        val main = composeTestRule.onNode(hasTestTag("Test CoinList"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val item1 =
            composeTestRule.onNode(hasTestTag("Test ${list[0].name}"), useUnmergedTree = true)
        item1.assertIsDisplayed()

        val item2 =
            composeTestRule.onNode(hasTestTag("Test ${list[1].name}"), useUnmergedTree = true)
        item2.assertIsDisplayed()

        val item3 =
            composeTestRule.onNode(hasTestTag("Test ${list[2].name}"), useUnmergedTree = true)
        item3.assertIsDisplayed()
    }

    @Test
    fun testCoinListFiltered() {
        val list = getCoinList()
        composeTestRule.setContent {
            CoinList(list, mockk())
        }
        val main = composeTestRule.onNode(hasTestTag("Test CoinList"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val searchBar = composeTestRule.onNode(hasTestTag("Test SearchBar"), useUnmergedTree = true)
        searchBar.assertIsDisplayed().performClick().performTextInput("coin")


        val item1 =
            composeTestRule.onNode(hasTestTag("Test ${list[0].name}"), useUnmergedTree = true)
        item1.assertIsDisplayed()

        val item2 =
            composeTestRule.onNode(hasTestTag("Test ${list[1].name}"), useUnmergedTree = true)
        item2.assertIsDisplayed()

        val item3 =
            composeTestRule.onNode(hasTestTag("Test ${list[2].name}"), useUnmergedTree = true)
        item3.assertDoesNotExist()
    }


    private fun getCoinList(): List<Coin> {
        val coin = Coin(
            id = "id1234",
            isActive = true,
            isNew = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "symbol",
            type = "type",
        )
        return listOf(coin, coin.copy(name = "Dogecoin"), coin.copy(name = "Hex"))
    }
}