package com.example.kotlindemo.repository.model

data class LinksExtended(
    val stats: Stats = Stats(),
    val type: String = "",
    val url: String = ""
)