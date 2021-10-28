package com.spear.coindemo.viewmodel.coin_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.spear.coindemo.presentation.MainActivity
import com.spear.coindemo.repository.model.Tag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TagsGridTests {

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
        val tags = getTags()
        composeTestRule.setContent {
            TagsGrid(tags)
        }
        val main = composeTestRule.onNode(hasTestTag("Test TagsGrid"), useUnmergedTree = true)
        main.assertIsDisplayed()

        val tag1 =
            composeTestRule.onNode(hasTestTag("Test Tag ${tags[0].name}"), useUnmergedTree = true)
        tag1.assertIsDisplayed()
        tag1.assertTextEquals("Tag 1")

        val tag2 =
            composeTestRule.onNode(hasTestTag("Test Tag ${tags[1].name}"), useUnmergedTree = true)
        tag2.assertIsDisplayed()
        tag2.assertTextEquals("Tag 2")

        val tag3 =
            composeTestRule.onNode(hasTestTag("Test Tag ${tags[2].name}"), useUnmergedTree = true)
        tag3.assertIsDisplayed()
        tag3.assertTextEquals("Tag 3")

    }

    private fun getTags(): List<Tag> =
        listOf(Tag(name = "Tag 1"), Tag(name = "Tag 2"), Tag(name = "Tag 3"))
}