package com.spear.coindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey()
    val id: String = "",
)