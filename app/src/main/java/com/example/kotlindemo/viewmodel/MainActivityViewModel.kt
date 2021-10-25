package com.example.kotlindemo.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlindemo.common.Constants
import com.example.kotlindemo.presentation.CoinDetailsScreen
import com.example.kotlindemo.presentation.CoinListScreen
import com.example.kotlindemo.presentation.OnboardingScreen
import com.example.kotlindemo.repository.AppRepository
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val appRepository: AppRepository
) : ViewModel() {

    @Composable
    fun MainContent() {
        val onboardState = appRepository.isOnboarded().collectAsState(initial = null)
        onboardState.value?.let { onboarded ->
            if (!onboarded) OnboardingScreen()
            else {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(
            modifier = Modifier.testTag("NavHost"),
            navController = navController,
            startDestination = "coin_list_screen"
        ) {
            composable("coin_list_screen") { CoinListScreen(navController) }
            composable("coin_detail_screen/{coinId}",
                arguments = listOf(
                    navArgument(Constants.PARAM_COIN_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                CoinDetailsScreen()
            }
        }
    }
}
