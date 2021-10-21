package com.example.kotlindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksExtended(
    val stats: Stats = Stats(),
    val type: String = "",
    val url: String = ""
)