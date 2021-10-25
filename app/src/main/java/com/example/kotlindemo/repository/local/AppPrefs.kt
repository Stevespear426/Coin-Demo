package com.example.kotlindemo.repository.local

import kotlinx.coroutines.flow.Flow

interface AppPrefs {
    suspend fun setOnboarded()
    fun isOnboarded(): Flow<Boolean>
    suspend fun clear()
}