package com.example.kotlindemo.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlindemo.repository.model.Coin
import com.example.kotlindemo.repository.model.CoinDetail
import com.example.kotlindemo.repository.model.DataTimeStamps

@Database(entities = [Coin::class, DataTimeStamps::class, CoinDetail::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class CoinsDb : RoomDatabase() {
    abstract fun coinsDao(): CoinsDao
}