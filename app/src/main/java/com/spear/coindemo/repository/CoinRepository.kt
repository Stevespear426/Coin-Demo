package com.spear.coindemo.repository

import com.spear.coindemo.repository.local.CoinsDao
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.model.DataTimeStamps
import com.spear.coindemo.repository.remote.CoinService
import javax.inject.Inject

class CoinRepository @Inject constructor(var coinService: CoinService, var coinsDao: CoinsDao) {

    suspend fun getCoins() : List<Coin> {
        val coinTimeStamp = coinsDao.getTimeStamps() ?: DataTimeStamps()
        var coins = coinsDao.getAllCoins()
        if (coins.isNullOrEmpty() || coinTimeStamp.coinsExpired()) {
            coins = coinService.getCoins()
            coinsDao.insertAllCoins(coins.toTypedArray())
            coinTimeStamp.updateCoinTimestamp()
            coinsDao.insertTimeStamps(coinTimeStamp)
        }
        return coins
    }


    suspend fun getCoinDetails(id: String): CoinDetail? {
        var coinDetails = coinsDao.getCoinDetails(id)
        if (coinDetails == null || coinDetails.expired()) {
            coinDetails = coinService.getCoinDetails(id).apply { updateTimestamp() }
            coinsDao.insertCoinDetail(coinDetails)
        }
        return coinDetails
    }
}