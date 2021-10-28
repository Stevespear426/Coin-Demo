package com.spear.coindemo.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spear.coindemo.viewmodel.favorites.FavoriteCoinsListViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoriteCoinsListViewModel = hiltViewModel(),
) {
    viewModel.MainContent {
        navController.navigate("${Screen.CoinDetailScreen.route}/${it}")
    }
}
