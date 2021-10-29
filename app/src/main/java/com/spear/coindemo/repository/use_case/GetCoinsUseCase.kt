package com.spear.coindemo.repository.use_case

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(repository.getCoins()))
    }.catch { e ->
        emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
    }
}