package com.example.kotlindemo.repository.use_case

import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.CoinRepository
import com.example.kotlindemo.repository.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail?>> = channelFlow {
        try {
            send(Resource.Loading())
            val coinDetail = repository.getCoinDetails(coinId)
            send(Resource.Success(coinDetail))
        } catch (e: Exception) {
            send(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}