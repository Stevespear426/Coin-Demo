package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kotlindemo.viewmodel.CoinsListViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel()) {
    viewModel.MainContent {
        navController.navigate("coin_detail_screen/${it}")
    }
}