package com.spear.coindemo.viewmodel.coin_detail

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.spear.coindemo.R
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.model.Tag
import com.spear.coindemo.repository.model.Team

@Composable
fun CoinDetailView(coin: CoinDetail) {
    LazyColumn(
        Modifier
            .testTag("Test CoinDetailView")
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))
            CoinTitleItem(coin = coin)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                coin.description ?: "",
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface),
            )

            coin.tags?.let { tags ->
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.tags),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface)
                )
                Spacer(modifier = Modifier.height(5.dp))
                TagsGrid(tags)
            }
            coin.team?.let {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.team_members),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface)
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
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
                Team(name = "John Doe", position = "Dev"),
                Team(name = "Jane Doe", position = "Wife")
            )
        )
    )
    override val count: Int = 1
}