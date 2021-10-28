package com.spear.coindemo.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object CoinListScreen : Screen("coin_list_screen", "Coins", Icons.Default.List)
    object CoinDetailScreen : Screen("coin_detail_screen", "Details", Icons.Default.Info)
    object SettingsScreen : Screen("settings_screen", "Settings", Icons.Default.Settings)
    object FavoritesScreen : Screen("favorites_screen", "Favorites", Icons.Default.Favorite)
}