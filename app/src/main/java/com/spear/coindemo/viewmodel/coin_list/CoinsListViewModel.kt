package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.spear.coindemo.R
import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    private val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().flowOn(Dispatchers.IO).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    @Composable
    fun MainContent(onClick: (id: String) -> Unit) {
        val coinState = state.value
        Box(
            Modifier
                .fillMaxSize()
                .testTag("Test CoinListViewModel MainContent")
        ) {
            when {
                coinState.coins.isNotEmpty() -> {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(coinState.isLoading),
                        onRefresh = { getCoins() },
                    ) {
                        CoinList(coinState.coins, onClick)
                    }
                }
                coinState.isLoading -> {
                    CircularProgressIndicator(
                        Modifier
                            .align(Alignment.Center)
                            .testTag("Spinner")
                    )
                }
                else -> Text(stringResource(R.string.coins_not_found), Modifier.testTag("Error"))
            }
        }
    }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)