package com.example.kotlindemo.repository.model

data class LinksExtended(
    val contributors: Int = 0,
    val followers: Int = 0,
    val stars: Int = 0,
    val subscribers: Int = 0,
    val type: String = "",
    val url: String = ""
)