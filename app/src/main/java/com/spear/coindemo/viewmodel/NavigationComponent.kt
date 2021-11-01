package com.spear.coindemo.viewmodel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.spear.coindemo.common.Constants
import com.spear.coindemo.presentation.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationComponent(navController: NavHostController, setScreen: (screen: Screen) -> Unit) {
    val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioNoBouncy)
    AnimatedNavHost(
        modifier = Modifier.testTag("Test NavHost"),
        navController = navController,
        startDestination = Screen.CoinListScreen.route,
    ) {
        composable(
            Screen.CoinListScreen.route
        ) {
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
            CoinDetailsScreen()
        }
        composable(Screen.SettingsScreen.route) {
            setScreen(Screen.SettingsScreen)
            SettingsScreen()
        }
        composable(Screen.FavoritesScreen.route) {
            setScreen(Screen.FavoritesScreen)
            FavoritesScreen(navController)
        }
    }
}