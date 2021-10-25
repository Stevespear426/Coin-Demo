package com.example.kotlindemo.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAppPrefs : AppPrefs {
    override suspend fun setOnboarded() {}
    override fun isOnboarded(): Flow<Boolean> = flow {
        emit(true)
    }
    override suspend fun clear() {}
}