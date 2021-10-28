package com.spear.coindemo.repository.use_case.favorites

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(val repository: CoinRepository) {
    operator fun invoke(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        repository.deleteFavorite(id)
        emit(Resource.Success(true))
    }.catch { e ->
        emit(Resource.Error(false, e.localizedMessage ?: "Unexpected error"))
    }
}