package com.example.kotlindemo.viewmodel.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(onComplete: () -> Unit) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .testTag("Onboarding")
    ) {
        val items = OnboardingItemData.items
        val state = rememberPagerState()

        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .testTag("Pager"),
            count = items.size
        ) { page -> OnboardingItem(items[page]) }
        BottomSection(size = items.size, index = state.currentPage) {
            if (state.currentPage + 1 < items.size)
                scope.launch {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            else onComplete()
        }
    }
}

@Preview
@Composable
fun PreviewOnboarding() {
    Onboarding {}
}