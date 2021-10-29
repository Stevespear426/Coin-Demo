package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spear.coindemo.repository.model.ChangeDuration
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.Quotes
import com.spear.coindemo.repository.model.USD
import java.text.NumberFormat

@Composable
fun CoinItem(coin: Coin, changeDuration: ChangeDuration, onClick: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(coin.id) }
            .padding(20.dp)
            .testTag("Test ${coin.name}"),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .testTag("Main Text")
        )
        coin.quotes?.USD?.let { usd ->
            Text(
                getPrice(usd, changeDuration),
                color = getTickerColor(usd.change15m),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.testTag("Test Price")
            )
        }
    }
}

fun getPrice(usd: USD, changeDuration: ChangeDuration): String {

    usd.price?.let { price ->
        val formattedPrice = NumberFormat.getCurrencyInstance().format(price)
        when (changeDuration) {
            ChangeDuration.DAY -> usd.change24h?.let { return "$formattedPrice (${it}%)" }
            ChangeDuration.WEEK -> usd.change7d?.let { return "$formattedPrice (${it}%)" }
            ChangeDuration.MONTH -> usd.change30d?.let { return "$formattedPrice (${it}%)" }
            ChangeDuration.YEAR -> usd.change1y?.let { return "$formattedPrice (${it}%)" }
            else -> usd.change1h?.let { return "$formattedPrice (${it}%)" }
        }
        return formattedPrice
    }
    return "Unavailable"
}

fun getTickerColor(price: Double?): Color {

    price?.let {
        return if (it > 0) Color.Green else Color.Red
    }
    return Color.Red
}

@Preview
@Composable
fun Preview() {
    val coin = Coin(
        rank = 1,
        name = "Coin",
        symbol = "cn",
        id = "cn",
        quotes = Quotes(USD(price = 5.00, change1h = 0.15))
    )
    CoinItem(coin = coin, ChangeDuration.HOURS_1, onClick = {})
}