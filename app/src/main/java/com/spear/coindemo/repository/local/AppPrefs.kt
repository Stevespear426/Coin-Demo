package com.spear.coindemo.repository.local

import kotlinx.coroutines.flow.Flow

interface AppPrefs {
    suspend fun setOnboarded(onBoarded: Boolean = true)
    fun isOnboarded(): Flow<Boolean>
    suspend fun clear()
}