package com.spear.coindemo.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class USD(
    val price: Double? = null,
    @Json(name = "ath_date")
    val athDate: String? = null,
    @Json(name = "ath_price")
    val athPrice: Double? = null,
    @Json(name = "market_cap")
    val marketCap: Long? = null,
    @Json(name = "market_cap_change_24h")
    val marketCapChange24h: Double? = null,
    @Json(name = "percent_change_15m")
    val change15m: Double? = null,
    @Json(name = "percent_change_30m")
    val change30m: Double? = null,
    @Json(name = "percent_change_1h")
    val change1h: Double? = null,
    @Json(name = "percent_change_6h")
    val change6h: Double? = null,
    @Json(name = "percent_change_12h")
    val change12h: Double? = null,
    @Json(name = "percent_change_24h")
    val change24h: Double? = null,
    @Json(name = "percent_change_7d")
    val change7d: Double? = null,
    @Json(name = "percent_change_30d")
    val change30d: Double? = null,
    @Json(name = "percent_change_1y")
    val change1y: Double? = null,
    @Json(name = "percent_from_price_ath")
    val changeTotal: Double? = null,
    @Json(name = "volume_24h")
    val volume24h: Double? = null,
    @Json(name = "volume_24h_change_24h")
    val volume24hChange24h: Double? = null
)