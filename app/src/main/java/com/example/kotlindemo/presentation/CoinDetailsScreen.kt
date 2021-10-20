package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotlindemo.viewmodel.coin_detail.CoinDetailViewModel

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) = viewModel.MainContent()