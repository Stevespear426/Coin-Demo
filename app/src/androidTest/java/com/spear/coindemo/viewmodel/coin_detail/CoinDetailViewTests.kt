package com.spear.coindemo.viewmodel.coin_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.spear.coindemo.MainActivity
import com.spear.coindemo.repository.model.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinDetailViewTests {

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
    fun testCompleteCoinDetailView() {
        composeTestRule.setContent {
            CoinDetailView(getCoinDetails())
        }
        val main =
            composeTestRule.onNode(hasTestTag("Test CoinDetailView"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val tags =
            composeTestRule.onNode(hasTestTag("Test TagsGrid"), useUnmergedTree = true)
        tags.assertIsDisplayed()

        val teamMember1 =
            composeTestRule.onNode(hasTestTag("Test TeamItem teamId1"), useUnmergedTree = true)
        teamMember1.assertIsDisplayed()
    }

    @Test
    fun testCoinDetailViewNoTeamOrTags() {
        composeTestRule.setContent {
            CoinDetailView(getCoinDetails().copy(team = null, tags = null))
        }
        val main =
            composeTestRule.onNode(hasTestTag("Test CoinDetailView"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val tags =
            composeTestRule.onNode(hasTestTag("Test TagsGrid"), useUnmergedTree = true)
        tags.assertDoesNotExist()

        val teamMember1 =
            composeTestRule.onNode(hasTestTag("Test TeamItem teamId1"), useUnmergedTree = true)
        teamMember1.assertDoesNotExist()
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
        team = listOf(Team("teamId1", "Team")),
        type = "type",
        whitepaper = Whitepaper(link = "link"),
    )
}