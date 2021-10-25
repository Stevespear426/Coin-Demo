package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlindemo.repository.model.Coin

@Composable
fun CoinItem(coin: Coin, onClick: (id: String) -> Unit) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        shape = RoundedCornerShape(5),
        color = Color.DarkGray
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(coin.id) }
                .padding(20.dp)
                .testTag("Row"),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "${coin.rank}. ${coin.name} (${coin.symbol})",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .testTag("Main Text")
            )
            Text(
                if (coin.isActive) "Active" else "Inactive",
                color = if (coin.isActive) Color.Green else Color.Red,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.testTag("Active Text")
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    val coin = Coin(
        rank = 1,
        name = "Coin",
        symbol = "cn",
        isActive = true,
        isNew = false,
        id = "cn",
        type = "coin"
    )
    CoinItem(coin = coin, onClick = {})
}