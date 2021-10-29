package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spear.coindemo.repository.model.ChangeDuration
import com.spear.coindemo.repository.model.Coin

@Composable
fun CoinList(coins: List<Coin>, onClick: (id: String) -> Unit) {
    var filter by remember { mutableStateOf("") }
    var changeDuration by remember { mutableStateOf(ChangeDuration.HOURS_1) }
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .testTag("Test CoinList"),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item { SearchBar(filter) { filter = it } }
        item { DurationRow(changeDuration) { changeDuration = it } }
        items(coins.filter { it.name.contains(filter, true) }) { coin ->
            CoinItem(coin, changeDuration, onClick)
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
        }
    }
}

@Preview
@Composable
fun CoinListPreview() {
    val coin = Coin(
        rank = 1,
        name = "Coin",
        symbol = "cn",
        id = "cn",
    )
    val list = listOf(coin, coin.copy(rank = 2), coin.copy(rank = 3), coin.copy(rank = 4))
    CoinList(coins = list, onClick = {})
}