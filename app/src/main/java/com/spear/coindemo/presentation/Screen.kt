package com.spear.coindemo.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.spear.coindemo.R

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object CoinListScreen : Screen("coin_list_screen", R.string.coins, Icons.Default.List)
    object CoinDetailScreen : Screen("coin_detail_screen", R.string.details, Icons.Default.Info)
    object SettingsScreen : Screen("settings_screen", R.string.settings, Icons.Default.Settings)
    object FavoritesScreen : Screen("favorites_screen", R.string.favorites, Icons.Default.Favorite)
}