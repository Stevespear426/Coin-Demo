package com.spear.coindemo.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.model.DataTimeStamps

@Database(entities = [Coin::class, DataTimeStamps::class, CoinDetail::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class CoinsDb : RoomDatabase() {
    abstract fun coinsDao(): CoinsDao
}