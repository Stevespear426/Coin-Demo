package com.example.kotlindemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlindemo.common.Constants.PARAM_COIN_ID
import com.example.kotlindemo.ui.theme.KotlinDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            KotlinDemoTheme {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "coin_list_screen") {
            composable("coin_list_screen") { CoinListScreen(navController) }
            composable("coin_detail_screen/{coinId}",
                arguments = listOf(
                    navArgument(PARAM_COIN_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                CoinDetailsScreen()
            }
        }
    }
}