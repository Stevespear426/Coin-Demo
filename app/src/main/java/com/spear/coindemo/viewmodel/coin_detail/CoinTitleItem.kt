package com.spear.coindemo.viewmodel.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.viewmodel.favorites.FavoritesBadgeViewModel

@Composable
fun CoinTitleItem(
    coin: CoinDetail,
    favViewModel: FavoritesBadgeViewModel = hiltViewModel()
) {
    favViewModel.isFavorite(coin.id)
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .testTag("Main Text")
        )
        favViewModel.MainContent(favId = coin.id)
    }
}