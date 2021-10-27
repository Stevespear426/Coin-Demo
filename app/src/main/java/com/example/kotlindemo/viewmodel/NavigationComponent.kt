package com.example.kotlindemo.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kotlindemo.common.Constants
import com.example.kotlindemo.presentation.*

@Composable
fun NavigationComponent(navController: NavHostController, setScreen: (screen: Screen) -> Unit) {
    NavHost(
        modifier = Modifier.testTag("Test NavHost"),
        navController = navController,
        startDestination = Screen.CoinListScreen.route
    ) {
        composable(Screen.CoinListScreen.route) {
            setScreen(Screen.CoinListScreen)
            CoinListScreen(navController)
        }
        composable("${Screen.CoinDetailScreen.route}/{coinId}",
            arguments = listOf(
                navArgument(Constants.PARAM_COIN_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            setScreen(Screen.CoinDetailScreen)
            CoinDetailsScreen()
        }
        composable(Screen.SettingsScreen.route) {
            setScreen(Screen.SettingsScreen)
            SettingsScreen()
        }
        composable(Screen.FavoritesScreen.route) {
            setScreen(Screen.FavoritesScreen)
            FavoritesScreen()
        }
    }
}