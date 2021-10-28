package com.spear.coindemo.repository.use_case.favorites

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.Favorite
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetFavoriteCoinsUseCase @Inject constructor(val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        val coins = repository.getCoins()
        repository.getFavorites().onEach { favorites ->
            val favoriteCoins = coins.filter { favorites.contains(Favorite(it.id)) }
            emit(Resource.Success(favoriteCoins))
        }.collect()
    }.catch { e ->
        emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
    }
}