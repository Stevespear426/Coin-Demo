package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.spear.coindemo.common.Resource
import com.spear.coindemo.MainActivity
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.use_case.GetCoinsUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@HiltAndroidTest
class CoinsListViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: CoinsListViewModel

    @MockK
    lateinit var mockUseCase: GetCoinsUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun mainContentUseCaseSuccess() {
        every { mockUseCase() } returns flow {
            emit(Resource.Success(getCoins()))
        }
        viewModel = CoinsListViewModel(mockUseCase)
        composeTestRule.setContent {
            viewModel.MainContent {}
        }

        val lColumn = composeTestRule.onNode(hasTestTag("Test CoinList"), useUnmergedTree = true)
        lColumn.assertIsDisplayed()
    }

    @Test
    fun mainContentUseCaseLoading() {
        every { mockUseCase() } returns flow {
            emit(Resource.Loading<List<Coin>>())
        }
        viewModel = CoinsListViewModel(mockUseCase)
        composeTestRule.setContent {
            viewModel.MainContent {}
        }

        val spinner = composeTestRule.onNode(hasTestTag("Spinner"), useUnmergedTree = true)
        spinner.assertIsDisplayed()
    }

    @Test
    fun mainContentUseCaseError() {
        every { mockUseCase() } returns flow<Resource<List<Coin>>> {
            throw IOException()
        }.catch { e -> emit(Resource.Error(message = e?.localizedMessage ?: "Error")) }
        viewModel = CoinsListViewModel(mockUseCase)
        composeTestRule.setContent {
            viewModel.MainContent {}
        }

        val errorText = composeTestRule.onNode(hasTestTag("Error"), useUnmergedTree = true)
        errorText.assertIsDisplayed()
    }

    private fun getCoins(): List<Coin> {
        return listOf(
            Coin(
                "id1234",
                name = "Name",
                rank = 1,
                symbol = "nme",
            )
        )
    }
}