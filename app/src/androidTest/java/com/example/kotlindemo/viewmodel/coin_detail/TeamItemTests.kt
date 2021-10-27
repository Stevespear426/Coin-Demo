package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.kotlindemo.presentation.MainActivity
import com.example.kotlindemo.repository.model.Team
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TeamItemTests {

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
    fun testTagsGrid() {
        val member = Team("id123", "First Last", "Dev")
        composeTestRule.setContent {
            TeamItem(member)
        }
        val main =
            composeTestRule.onNode(hasTestTag("Test TeamItem ${member.id}"), useUnmergedTree = true)
        main.assertIsDisplayed()
        main.assertTextEquals("First Last (Dev)")
    }
}