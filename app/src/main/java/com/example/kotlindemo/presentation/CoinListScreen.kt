package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlindemo.viewmodel.coin_list.CoinsListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel(),
) {
    viewModel.MainContent {
        navController.navigate("${Screen.CoinDetailScreen.route}/${it}")
    }
}