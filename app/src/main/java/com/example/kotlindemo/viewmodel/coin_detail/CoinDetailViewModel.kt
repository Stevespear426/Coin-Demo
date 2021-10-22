package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo.common.Constants.PARAM_COIN_ID
import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.model.CoinDetail
import com.example.kotlindemo.repository.use_case.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState(coinDetail = null))
    private val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinDetailUseCase(coinId).flowOn(Dispatchers.IO).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coinDetail = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "Error", coinDetail = null)
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true, coinDetail = null)
                }
            }
        }.launchIn(viewModelScope)
    }

    @Composable
    fun MainContent() {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
            when {
                state.value.coinDetail != null -> {
                    state.value.coinDetail?.let { coin ->
                        CoinDetailView(coin)
                    }
                }
                state.value.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .testTag("Spinner")
                    )
                }
                else -> Text(modifier = Modifier.testTag("Error"), text = "Coin not found")
            }

        }
    }
}

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail?,
    val error: String = ""
)