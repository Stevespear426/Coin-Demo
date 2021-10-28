package com.spear.coindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import  com.spear.coindemo.viewmodel.coin_list.CoinsListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel(),
) {
    viewModel.MainContent {
        navController.navigate("${Screen.CoinDetailScreen.route}/${it}")
    }
}