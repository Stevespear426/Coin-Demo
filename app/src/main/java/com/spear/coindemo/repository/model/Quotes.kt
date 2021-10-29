package com.spear.coindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quotes(
    val USD: USD? = null
)