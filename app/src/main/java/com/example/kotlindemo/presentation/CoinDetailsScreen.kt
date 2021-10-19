package com.example.kotlindemo.presentation

import androidx.compose.runtime.Composable
import com.example.kotlindemo.viewmodel.CoinDetailViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()) {
    viewModel.MainContent()
}