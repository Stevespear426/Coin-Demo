package com.spear.coindemo.viewmodel

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.spear.coindemo.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScaffoldTests {

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
    fun testAllComposablesDisplayed() {
        composeTestRule.setContent {
            MainScaffold()
        }

        val navHost =
            composeTestRule.onNode(hasTestTag("Test NavHost"), useUnmergedTree = true)
        navHost.assertIsDisplayed()

        val bottomBar =
            composeTestRule.onNode(hasTestTag("Test AppBottomBar"), useUnmergedTree = true)
        bottomBar.assertIsDisplayed()

        val coinList =
            composeTestRule.onNode(
                hasTestTag("Test BottomBar CoinListScreen"),
                useUnmergedTree = true
            )
        coinList.assertIsDisplayed()

        val favorites =
            composeTestRule.onNode(
                hasTestTag("Test BottomBar FavoritesScreen"),
                useUnmergedTree = true
            )
        favorites.assertIsDisplayed()

        val settings =
            composeTestRule.onNode(
                hasTestTag("Test BottomBar SettingsScreen"),
                useUnmergedTree = true
            )
        settings.assertIsDisplayed()

        val coinListScreen =
            composeTestRule.onNode(
                hasTestTag("Test CoinListViewModel MainContent"),
                useUnmergedTree = true
            )
        coinListScreen.assertIsDisplayed()
    }

    @Test
    fun testNavigateToFavorites() {
        composeTestRule.setContent {
            MainScaffold()
        }

        val favorites =
            composeTestRule.onNode(
                hasTestTag("Test BottomBar FavoritesScreen"),
                useUnmergedTree = true
            )
        favorites.assertIsDisplayed()
        favorites.performClick()

        val favoritesScreen =
            composeTestRule.onNode(
                hasTestTag("Test FavoritesListViewModel MainContent"),
                useUnmergedTree = true
            )
        favoritesScreen.assertIsDisplayed()
    }

    @Test
    fun testNavigateToSettings() {
        composeTestRule.setContent {
            MainScaffold()
        }

        val settings =
            composeTestRule.onNode(
                hasTestTag("Test BottomBar SettingsScreen"),
                useUnmergedTree = true
            )
        settings.assertIsDisplayed()
        settings.performClick()

        val settingsScreen =
            composeTestRule.onNode(
                hasTestTag("Test SettingsScreen MainContent"),
                useUnmergedTree = true
            )
        settingsScreen.assertIsDisplayed()
    }


}