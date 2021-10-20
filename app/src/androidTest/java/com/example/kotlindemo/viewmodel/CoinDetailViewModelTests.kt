package com.example.kotlindemo.viewmodel

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.SavedStateHandle
import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.*
import com.example.kotlindemo.repository.use_case.GetCoinDetailUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.channelFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CoinDetailViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: CoinDetailViewModel

    @MockK
    lateinit var mockUseCase: GetCoinDetailUseCase

    @RelaxedMockK
    lateinit var mockSavedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { mockSavedStateHandle.get<String>(any()) } returns ""
    }

    @Test
    fun mainContentUseCaseSuccess() {
        every { mockUseCase.invoke(any()) } returns channelFlow {
            send(Resource.Success(getCoinDetails()))
        }
        viewModel = CoinDetailViewModel(mockUseCase, mockSavedStateHandle)
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val lColumn = composeTestRule.onNode(hasTestTag("MainColumn"), useUnmergedTree = true)
        lColumn.assertIsDisplayed()
    }

    @Test
    fun mainContentUseCaseLoading() {
        every { mockUseCase.invoke(any()) } returns channelFlow {
            send(Resource.Loading<CoinDetail?>())
        }
        viewModel = CoinDetailViewModel(mockUseCase, mockSavedStateHandle)
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val spinner = composeTestRule.onNode(hasTestTag("Spinner"), useUnmergedTree = true)
        spinner.assertIsDisplayed()
    }

    @Test
    fun mainContentUseCaseError() {
        every { mockUseCase.invoke(any()) } returns channelFlow {
            send(Resource.Error<CoinDetail?>(message = ""))
        }
        viewModel = CoinDetailViewModel(mockUseCase, mockSavedStateHandle)
        composeTestRule.setContent {
            viewModel.MainContent()
        }

        val errorText = composeTestRule.onNode(hasTestTag("Error"), useUnmergedTree = true)
        errorText.assertIsDisplayed()
    }

    private fun getCoinDetails(): CoinDetail? = CoinDetail(
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