package com.spear.coindemo.repository.remote

import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {
    @GET("v1/tickers")
    suspend fun getCoins(): List<Coin>

    @GET("v1/coins/{id}")
    suspend fun getCoinDetails(@Path("id")  id: String): CoinDetail
}