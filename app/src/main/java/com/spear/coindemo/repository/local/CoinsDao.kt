package com.spear.coindemo.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins")
    fun getAllCoins(): List<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCoins(objects: Array<Coin>)

    @Query("DELETE FROM coins")
    fun deleteAllCoins()

    @Query("SELECT * FROM coin_details WHERE id = :id")
    fun getCoinDetails(id: String): CoinDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinDetail(detail: CoinDetail)

    @Query("DELETE FROM coin_details")
    fun deleteAllCoinDetails()

    @Query("DELETE FROM coin_details WHERE id = :id")
    fun deleteCoinDetails(id: String)

    @Query("SELECT * FROM favorites")
    fun getFavoritesFlow(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites")
    fun deleteAllFavorites()

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteFavorite(id: String)
}