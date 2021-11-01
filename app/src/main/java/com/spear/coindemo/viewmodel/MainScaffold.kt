package com.spear.coindemo.viewmodel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.spear.coindemo.presentation.Screen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScaffold() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberAnimatedNavController()
    val currentScreen = remember { mutableStateOf<Screen>(Screen.CoinListScreen) }
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            NavigationComponent(navController) { screen ->
                currentScreen.value = screen
            }
        },
        bottomBar = {
            AppBottomBar(currentScreen.value) { route ->
                navController.navigate(route) {
                    launchSingleTop = true
                    popUpTo(Screen.CoinListScreen.route) { inclusive = false }
                }
            }
        }
    )
}