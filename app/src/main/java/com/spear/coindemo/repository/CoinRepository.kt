package com.spear.coindemo.repository

import com.spear.coindemo.repository.local.CoinsDao
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.model.Favorite
import com.spear.coindemo.repository.remote.CoinService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepository @Inject constructor(var coinService: CoinService, var coinsDao: CoinsDao) {

    suspend fun getCoins(): List<Coin> {
        return try {
            val coins = coinService.getCoins()
            coinsDao.insertAllCoins(coins.toTypedArray())
            coins
        } catch (e: Exception) {
            coinsDao.getAllCoins()
        }
    }


    suspend fun getCoinDetails(id: String): CoinDetail? {
        var coinDetails = coinsDao.getCoinDetails(id)
        if (coinDetails == null || coinDetails.expired()) {
            coinDetails = coinService.getCoinDetails(id).apply { updateTimestamp() }
            coinsDao.insertCoinDetail(coinDetails)
        }
        return coinDetails
    }

    fun getFavorites(): Flow<List<Favorite>> {
        return coinsDao.getFavoritesFlow()
    }

    fun addFavorite(favorite: Favorite) {
        coinsDao.insertFavorite(favorite)
    }

    fun deleteFavorite(id: String) {
        coinsDao.deleteFavorite(id)
    }

}