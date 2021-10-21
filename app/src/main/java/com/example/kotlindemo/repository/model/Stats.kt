package com.example.kotlindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    val contributors: Int = 0,
    val followers: Int = 0,
    val stars: Int = 0,
    val subscribers: Int = 0,
)