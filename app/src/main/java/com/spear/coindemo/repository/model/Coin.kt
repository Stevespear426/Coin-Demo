package com.spear.coindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "coins")
data class Coin(
    @PrimaryKey
    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    @Json(name = "beta_value")
    val betaValue: Double? = null,
    @Json(name = "circulating_supply")
    val circulatingSupply: Long? = null,
    @Json(name = "first_data_at")
    val firstDataAt: String? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null,
    @Json(name = "max_supply")
    val maxSupply: Long? = null,
    val quotes: Quotes? = null,
    @Json(name = "total_supply")
    val totalSupply: Long? = null
)