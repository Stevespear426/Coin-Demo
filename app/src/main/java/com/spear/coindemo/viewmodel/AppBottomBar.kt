package com.spear.coindemo.viewmodel

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.spear.coindemo.presentation.Screen
import com.spear.coindemo.ui.theme.LightBlue

@Composable
fun AppBottomBar(currentScreen: Screen, routeTo: (route: String) -> Unit) {
    BottomNavigation(
        Modifier.testTag("Test AppBottomBar")
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Screen.CoinListScreen.icon,
                    contentDescription = Screen.CoinListScreen.title
                )
            },
            label = {
                Text(
                    text = Screen.CoinListScreen.title
                )
            },
            selected = currentScreen == Screen.CoinListScreen || currentScreen == Screen.CoinDetailScreen,
            selectedContentColor = LightBlue,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = {
                routeTo(Screen.CoinListScreen.route)
            },
            modifier = Modifier.testTag("Test BottomBar CoinListScreen")
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Screen.FavoritesScreen.icon,
                    contentDescription = Screen.FavoritesScreen.title
                )
            },
            label = {
                Text(
                    text = Screen.FavoritesScreen.title
                )
            },
            selected = currentScreen == Screen.FavoritesScreen,
            selectedContentColor = LightBlue,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = {
                routeTo(Screen.FavoritesScreen.route)
            },
            modifier = Modifier.testTag("Test BottomBar FavoritesScreen")
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Screen.SettingsScreen.icon,
                    contentDescription = Screen.SettingsScreen.title
                )
            },
            label = {
                Text(
                    text = Screen.SettingsScreen.title
                )
            },
            selected = currentScreen == Screen.SettingsScreen,
            selectedContentColor = LightBlue,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = {
                routeTo(Screen.SettingsScreen.route)
            },
            modifier = Modifier.testTag("Test BottomBar SettingsScreen")
        )
    }
}

@Preview(name = "CoinListScreen")
@Composable
fun AppBottomBarPreviewCoinListScreen() {
    AppBottomBar(Screen.CoinListScreen) {}
}

@Preview(name = "FavoritesScreen")
@Composable
fun AppBottomBarPreviewFavoritesScreen() {
    AppBottomBar(Screen.FavoritesScreen) {}
}

@Preview(name = "SettingsScreen")
@Composable
fun AppBottomBarPreviewSettingsScreen() {
    AppBottomBar(Screen.SettingsScreen) {}
}