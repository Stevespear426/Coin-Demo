package com.spear.coindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import  com.spear.coindemo.viewmodel.coin_detail.CoinDetailViewModel

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) = viewModel.MainContent()