package com.spear.coindemo.repository.use_case

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail?>> = flow {
        emit(Resource.Loading())
        val coinDetail = repository.getCoinDetails(coinId)
        emit(Resource.Success(coinDetail))
    }.catch { e ->
        emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
    }
}