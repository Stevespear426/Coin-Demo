package com.example.kotlindemo.repository.use_case

import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.CoinRepository
import com.example.kotlindemo.repository.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = channelFlow {
        try {
            send(Resource.Loading())
            val coins = repository.getCoins()
            send(Resource.Success(coins))
        } catch (e: Exception) {
            send(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}