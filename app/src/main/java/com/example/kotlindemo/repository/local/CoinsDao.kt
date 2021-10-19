package com.example.kotlindemo.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlindemo.repository.model.Coin
import com.example.kotlindemo.repository.model.CoinDetail
import com.example.kotlindemo.repository.model.DataTimeStamps

@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins")
    fun getAllCoins(): List<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCoins(objects: Array<Coin>)

    @Query("DELETE FROM coins")
    fun deleteAllCoins()

    @Query("SELECT * FROM timestamps")
    fun getTimeStamps(): DataTimeStamps?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimeStamps(timeStamps: DataTimeStamps)

    @Query("DELETE FROM timestamps")
    fun deleteTimeStamps()

    @Query("SELECT * FROM coin_details WHERE id = :id")
    fun getCoinDetails(id: String): CoinDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinDetail(detail: CoinDetail)

    @Query("DELETE FROM coin_details")
    fun deleteAllCoinDetails()

    @Query("DELETE FROM coin_details WHERE id = :id")
    fun deleteCoinDetails(id: String)
}