package com.example.kotlindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

@Entity(tableName = "coin_details")
data class CoinDetail(
    val description: String?,
    @SerializedName("development_status")
    val developmentStatus: String?,
    @SerializedName("first_data_at")
    val firstDataAt: String?,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean?,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String?,
    @PrimaryKey
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("is_new")
    val isNew: Boolean?,
    @SerializedName("last_data_at")
    val lastDataAt: String?,
    val links: Links?,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>?,
    val message: String?,
    val name: String?,
    @SerializedName("open_source")
    val openSource: Boolean?,
    @SerializedName("org_structure")
    val orgStructure: String?,
    @SerializedName("proof_type")
    val proofType: String?,
    val rank: Int?,
    @SerializedName("started_at")
    val startedAt: String?,
    val symbol: String?,
    val tags: List<Tag>?,
    val team: List<Team>?,
    val type: String?,
    val whitepaper: Whitepaper?,
    var timestamp: Long = System.currentTimeMillis()
) {
    fun updateTimestamp() {
        timestamp = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)
    }

    fun expired(): Boolean {
        return Date().after(Date(timestamp))
    }
}