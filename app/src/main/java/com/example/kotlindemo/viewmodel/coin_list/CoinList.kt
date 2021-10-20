package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.kotlindemo.repository.model.Coin

@Composable
fun CoinList(coins: List<Coin>, onClick: (id: String) -> Unit) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(top = 10.dp)
            .testTag("LazyColumnTestId"),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(coins) { coin -> CoinItem(coin, onClick) }
    }
}