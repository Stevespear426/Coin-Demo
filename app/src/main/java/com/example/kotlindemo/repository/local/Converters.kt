package com.example.kotlindemo.repository.local

import androidx.room.TypeConverter
import com.example.kotlindemo.repository.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    val gson: Gson = Gson()

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun fromLinkString(value: String?): Links? {
        return value?.let { gson.fromJson<Links>(value) }
    }

    @TypeConverter
    fun linksToString(links: Links?): String = gson.toJson(links)

    @TypeConverter
    fun fromLinksExtendedString(value: String?): List<LinksExtended>? {
        return value?.let { gson.fromJson<List<LinksExtended>>(value) }
    }

    @TypeConverter
    fun linksExtendedToString(linksExtended: List<LinksExtended>?): String = gson.toJson(linksExtended)

    @TypeConverter
    fun fromWhitepaperString(value: String?): Whitepaper? {
        return value?.let { gson.fromJson<Whitepaper>(value) }
    }

    @TypeConverter
    fun whitepaperToString(whitepaper: Whitepaper?): String = gson.toJson(whitepaper)

    @TypeConverter
    fun fromTeamsString(value: String?): List<Team>? {
        return value?.let { gson.fromJson<List<Team>>(value) }
    }

    @TypeConverter
    fun teamsToString(teams: List<Team>?): String = gson.toJson(teams)

    @TypeConverter
    fun fromTagsString(value: String?): List<Tag>? {
        return value?.let { gson.fromJson<List<Tag>>(value) }
    }

    @TypeConverter
    fun tagsToString(tags: List<Tag>?): String = gson.toJson(tags)
}