package com.spear.coindemo.viewmodel.favorites

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.common.Resource
import com.spear.coindemo.presentation.MainActivity
import com.spear.coindemo.repository.use_case.favorites.AddFavoriteUseCase
import com.spear.coindemo.repository.use_case.favorites.DeleteFavoriteUseCase
import com.spear.coindemo.repository.use_case.favorites.IsFavoriteUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FavoriteBadgeViewModelTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: FavoritesBadgeViewModel

    @RelaxedMockK
    lateinit var isMockUseCase: IsFavoriteUseCase

    @RelaxedMockK
    lateinit var addMockUseCase: AddFavoriteUseCase

    @RelaxedMockK
    lateinit var deleteMockUseCase: DeleteFavoriteUseCase


    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = FavoritesBadgeViewModel(isMockUseCase, addMockUseCase, deleteMockUseCase)
    }

    @Test
    fun testIsFavorite() {
        every { isMockUseCase(any()) } returns flow {
            emit(Resource.Success(true))
        }
        viewModel.isFavorite("1234")
        composeTestRule.setContent {
            viewModel.MainContent("1234")
        }

        val badge = composeTestRule.onNode(
            hasTestTag("Test FavoriteBadge"),
            useUnmergedTree = true
        )
        badge.assertIsDisplayed()
        badge.performClick()
        verify { deleteMockUseCase("1234") }
    }

    @Test
    fun testIsNotFavorite() {
        every { isMockUseCase(any()) } returns flow {
            emit(Resource.Success(false))
        }
        viewModel.isFavorite("1234")
        composeTestRule.setContent {
            viewModel.MainContent("1234")
        }

        val badge = composeTestRule.onNode(
            hasTestTag("Test FavoriteBadge"),
            useUnmergedTree = true
        )
        badge.assertIsDisplayed()
        badge.performClick()
        verify { addMockUseCase("1234") }
    }
}