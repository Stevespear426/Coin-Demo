package com.example.kotlindemo.repository.local

import androidx.room.TypeConverter
import com.example.kotlindemo.repository.model.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    var moshiBuild = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> moshi(): JsonAdapter<T> = moshiBuild.adapter(T::class.java)
    inline fun <reified T, reified D> moshiList(): JsonAdapter<T> = moshiBuild.adapter(Types.newParameterizedType(List::class.java, D::class.java))

    @TypeConverter
    fun fromLinkString(value: String?): Links? {
        return value?.let { moshi<Links>().fromJson(value) }
    }

    @TypeConverter
    fun linksToString(links: Links?): String = moshi<Links>().toJson(links)

    @TypeConverter
    fun fromLinksExtendedString(value: String?): List<LinksExtended>? {
        return value?.let { moshiList<List<LinksExtended>, LinksExtended>().fromJson(value) }
    }

    @TypeConverter
    fun linksExtendedToString(linksExtended: List<LinksExtended>?): String {
      return moshiList<List<LinksExtended>, LinksExtended>().toJson(linksExtended)
    }

    @TypeConverter
    fun fromWhitepaperString(value: String?): Whitepaper? {
        return value?.let { moshi<Whitepaper>().fromJson(value) }
    }

    @TypeConverter
    fun whitepaperToString(whitepaper: Whitepaper?): String = moshi<Whitepaper>().toJson(whitepaper)

    @TypeConverter
    fun fromTeamsString(value: String?): List<Team>? {
        return value?.let { moshiList<List<Team>, Team>().fromJson(value) }
    }

    @TypeConverter
    fun teamsToString(teams: List<Team>?): String = moshiList<List<Team>, Team>().toJson(teams)

    @TypeConverter
    fun fromTagsString(value: String?): List<Tag>? {
        return value?.let { moshiList<List<Tag>, Tag>().fromJson(value) }
    }

    @TypeConverter
    fun tagsToString(tags: List<Tag>?): String = moshiList<List<Tag>, Tag>().toJson(tags)
}