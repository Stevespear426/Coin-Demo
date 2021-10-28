package com.spear.coindemo.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    @Json(name = "coin_counter")
    val coinCounter: Int = 0,
    @Json(name = "ico_counter")
    val icoCounter: Int = 0,
    val id: String = "",
    val name: String = ""
)