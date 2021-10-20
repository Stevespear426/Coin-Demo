package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.kotlindemo.repository.model.CoinDetail
import com.example.kotlindemo.repository.model.Tag
import com.example.kotlindemo.repository.model.Team

@Preview
@Composable
fun CoinDetailView(@PreviewParameter(SampleCoinDetail::class) coin: CoinDetail) {
    LazyColumn(
        Modifier
            .testTag("MainColumn")
            .padding(
                horizontal = 20.dp
            )
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))
            CoinTitleItem(coin)
            Spacer(modifier = Modifier.height(5.dp))
            Text(coin.description ?: "", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Tags", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(5.dp))
            coin.tags?.let { tags -> TagsGrid(tags) }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Team members", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(5.dp))
        }
        coin.team?.let { team ->
            items(team) { member -> TeamItem(member) }
        }
    }
}

class SampleCoinDetail : PreviewParameterProvider<CoinDetail> {
    override val values = sequenceOf(
        CoinDetail(
            rank = 1,
            name = "Coin",
            description = "This is a description of a coin. There is probably more information than this, but oh well. How does it look?",
            symbol = "cn",
            tags = listOf(
                Tag(name = "Nice"),
                Tag(name = "Cool"),
                Tag(name = "Awesome")
            ),
            team = listOf(
                Team(name = "Steve Spear", position = "Dev"),
                Team(name = "Kayla Spear", position = "Wife")
            )
        )
    )
    override val count: Int = 1
}