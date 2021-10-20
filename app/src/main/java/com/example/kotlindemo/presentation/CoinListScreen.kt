package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlindemo.viewmodel.coin_list.CoinsListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel()
) {
    viewModel.MainContent {
        navController.navigate("coin_detail_screen/${it}")
    }
}