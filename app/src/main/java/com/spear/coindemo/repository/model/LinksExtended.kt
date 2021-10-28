package com.spear.coindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksExtended(
    val stats: Stats = Stats(),
    val type: String = "",
    val url: String = ""
)