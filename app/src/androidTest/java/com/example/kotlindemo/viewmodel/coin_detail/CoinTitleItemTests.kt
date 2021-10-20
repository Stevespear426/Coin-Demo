package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinTitleItemTests {

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
            CoinTitleItem(coin = getCoinDetails())
        }
        val mainText = composeTestRule.onNode(hasTestTag("Main Text"), useUnmergedTree = true)
        mainText.assertIsDisplayed()
        mainText.assertTextEquals("1. name (symbol)")
    }

    @Test
    fun testActiveText() {
        composeTestRule.setContent {
            CoinTitleItem(coin = getCoinDetails())
        }
        val activeText = composeTestRule.onNode(hasTestTag("Active Text"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("Active")
    }

    @Test
    fun testInactiveText() {
        composeTestRule.setContent {
            CoinTitleItem(coin = getCoinDetails().copy(isActive = false))
        }
        val activeText = composeTestRule.onNode(hasTestTag("Active Text"), useUnmergedTree = true)
        activeText.assertIsDisplayed()
        activeText.assertTextEquals("Inactive")
    }

    private fun getCoinDetails(): CoinDetail = CoinDetail(
        description = "description",
        developmentStatus = "development_status",
        firstDataAt = "first_data_at",
        hardwareWallet = true,
        hashAlgorithm = "hash_algorithm",
        id = "id1234",
        isActive = true,
        isNew = true,
        lastDataAt = "last_data_at",
        links = Links(youtube = listOf("youtube")),
        linksExtended = listOf(LinksExtended(url = "www.url.com")),
        message = "message",
        name = "name",
        openSource = true,
        orgStructure = "org_structure",
        proofType = "proof_type",
        rank = 1,
        startedAt = "started_at",
        symbol = "symbol",
        tags = listOf(Tag(name = "Tag")),
        team = listOf(Team(name = "Team")),
        type = "type",
        whitepaper = Whitepaper(link = "link"),
    )
}