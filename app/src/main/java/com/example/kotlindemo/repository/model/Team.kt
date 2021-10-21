package com.example.kotlindemo.repository.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    val id: String = "",
    val name: String = "",
    val position: String = ""
)