package com.spear.coindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
@Entity(tableName = "coin_details")
data class CoinDetail(
    val description: String? = null,
    @Json(name = "development_status")
    val developmentStatus: String? = null,
    @Json(name = "first_data_at")
    val firstDataAt: String? = null,
    @Json(name = "hardware_wallet")
    val hardwareWallet: Boolean? = null,
    @Json(name = "hash_algorithm")
    val hashAlgorithm: String? = null,
    @PrimaryKey
    val id: String = "",
    @Json(name = "is_active")
    val isActive: Boolean? = null,
    @Json(name = "is_new")
    val isNew: Boolean? = null,
    @Json(name = "last_data_at")
    val lastDataAt: String? = null,
    val links: Links? = null,
    @Json(name = "links_extended")
    val linksExtended: List<LinksExtended>? = null,
    val message: String? = null,
    val name: String? = null,
    @Json(name = "open_source")
    val openSource: Boolean? = null,
    @Json(name = "org_structure")
    val orgStructure: String? = null,
    @Json(name = "proof_type")
    val proofType: String? = null,
    val rank: Int? = null,
    @Json(name = "started_at")
    val startedAt: String? = null,
    val symbol: String? = null,
    val tags: List<Tag>? = null,
    val team: List<Team>? = null,
    val type: String? = null,
    val whitepaper: Whitepaper? = null,
    var timestamp: Long = System.currentTimeMillis()
) {
    fun updateTimestamp() {
        timestamp = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)
    }

    fun expired(): Boolean {
        return Date().after(Date(timestamp))
    }
}