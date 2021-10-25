package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.SavedStateHandle
import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.hilt.AppModule
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.*
import com.example.kotlindemo.repository.use_case.GetCoinDetailUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@UninstallModules(AppModule::class)
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
        MockKAnnotations.init(this, relaxUnitFun = true)
        hiltRule.inject()
        every { mockSavedStateHandle.get<String>(any()) } returns ""
    }

    @Test
    fun mainContentUseCaseSuccess() {
        every { mockUseCase(any()) } returns flow {
            emit(Resource.Success(getCoinDetails()))
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
        every { mockUseCase(any()) } returns flow {
            emit(Resource.Loading<CoinDetail?>())
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
        every { mockUseCase(any()) } returns flow<Resource<CoinDetail?>> {
            throw IOException()
        }.catch { e -> emit(Resource.Error(message = e?.localizedMessage ?: "Error")) }
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