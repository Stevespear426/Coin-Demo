package com.example.kotlindemo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.concurrent.TimeUnit

@Entity(tableName = "coin_details")
data class CoinDetail(
    val description: String?,
    val development_status: String?,
    val first_data_at: String?,
    val hardware_wallet: Boolean?,
    val hash_algorithm: String?,
    @PrimaryKey
    val id: String = "",
    val is_active: Boolean?,
    val is_new: Boolean?,
    val last_data_at: String?,
    val links: Links?,
    val links_extended: List<LinksExtended>?,
    val message: String?,
    val name: String?,
    val open_source: Boolean?,
    val org_structure: String?,
    val proof_type: String?,
    val rank: Int?,
    val started_at: String?,
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