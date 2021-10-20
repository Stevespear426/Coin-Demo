package com.example.kotlindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

@Entity(tableName = "coin_details")
data class CoinDetail(
    val description: String? = null,
    @SerializedName("development_status")
    val developmentStatus: String? = null,
    @SerializedName("first_data_at")
    val firstDataAt: String? = null,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean? = null,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String? = null,
    @PrimaryKey
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean? = null,
    @SerializedName("is_new")
    val isNew: Boolean? = null,
    @SerializedName("last_data_at")
    val lastDataAt: String? = null,
    val links: Links? = null,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>? = null,
    val message: String? = null,
    val name: String? = null,
    @SerializedName("open_source")
    val openSource: Boolean? = null,
    @SerializedName("org_structure")
    val orgStructure: String? = null,
    @SerializedName("proof_type")
    val proofType: String? = null,
    val rank: Int? = null,
    @SerializedName("started_at")
    val startedAt: String? = null,
    val symbol: String? = null,
    val tags: List<Tag>? = null,
    val team: List<Team>? = null,
    val type: String? = null,
    val whitepaper: Whitepaper? = null,
    var timestamp: Long = System.currentTimeMillis()
) {
    fun updateTimestamp() {
        timestamp = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)
    }

    fun expired(): Boolean {
        return Date().after(Date(timestamp))
    }
}