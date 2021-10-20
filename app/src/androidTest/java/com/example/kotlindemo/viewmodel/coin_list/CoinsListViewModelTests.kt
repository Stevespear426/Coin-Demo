package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.Coin
import com.example.kotlindemo.repository.use_case.GetCoinsUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.channelFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        every { mockUseCase.invoke() } returns channelFlow {
            send(Resource.Success(getCoins()))
        }
        viewModel = CoinsListViewModel(mockUseCase)
        composeTestRule.setContent {
            viewModel.MainContent {}
        }

        val lColumn = composeTestRule.onNode(hasTestTag("LazyColumnTestId"), useUnmergedTree = true)
        lColumn.assertIsDisplayed()
    }

    @Test
    fun mainContentUseCaseLoading() {
        every { mockUseCase.invoke() } returns channelFlow {
            send(Resource.Loading<List<Coin>>())
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
        every { mockUseCase.invoke() } returns channelFlow {
            send(Resource.Error<List<Coin>>(message = ""))
        }
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
                isActive = true,
                isNew = false,
                name = "Name",
                rank = 1,
                symbol = "nme",
                type = "type"
            )
        )
    }
}