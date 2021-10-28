package com.spear.coindemo.viewmodel.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.spear.coindemo.repository.model.CoinDetail

@Preview
@Composable
fun CoinTitleItem(@PreviewParameter(SampleCoin::class) coin: CoinDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f).testTag("Main Text")
        )
        Text(
            if (coin.isActive == true) "Active" else "Inactive",
            color = if (coin.isActive == true) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.testTag("Active Text")
        )
    }
}

class SampleCoin : PreviewParameterProvider<CoinDetail> {
    override val values = sequenceOf(
        CoinDetail(rank = 1, name = "Coin", symbol = "cn")
    )
    override val count: Int = 1
}