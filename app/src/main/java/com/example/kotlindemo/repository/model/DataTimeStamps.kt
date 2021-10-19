package com.example.kotlindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.concurrent.TimeUnit

@Entity(tableName = "timestamps")
data class DataTimeStamps(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = -1,
    var coins: Long = System.currentTimeMillis(),
) {
    fun updateCoinTimestamp() {
        coins = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)
    }

    fun coinsExpired(): Boolean {
        return Date().after(Date(coins))
    }
}