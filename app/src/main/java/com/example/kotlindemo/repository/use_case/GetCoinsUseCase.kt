package com.example.kotlindemo.repository.use_case

import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.CoinRepository
import com.example.kotlindemo.repository.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        val coins = repository.getCoins()
        emit(Resource.Success(coins))
    }.catch { e ->
        emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
    }
}