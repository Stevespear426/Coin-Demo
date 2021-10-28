package com.spear.coindemo.repository.use_case.favorites

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.Favorite
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(val repository: CoinRepository) {
    operator fun invoke(id: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        repository.getFavorites().onEach { favorites ->
            emit(Resource.Success(favorites.contains(Favorite(id))))
        }.collect()
    }.catch { e ->
        emit(Resource.Error(false, e.localizedMessage ?: "Unexpected error"))
    }
}