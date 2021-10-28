package com.spear.coindemo.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    val explorer: List<String> = emptyList(),
    val facebook: List<String> = emptyList(),
    val reddit: List<String> = emptyList(),
    @Json(name = "source_code")
    val sourceCode: List<String> = emptyList(),
    val website: List<String> = emptyList(),
    val youtube: List<String> = emptyList()
)