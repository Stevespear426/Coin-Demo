package com.spear.coindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Whitepaper(
    val link: String? = "",
    val thumbnail: String? = ""
)