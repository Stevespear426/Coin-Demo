package com.spear.coindemo.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAppPrefs : AppPrefs {
    override suspend fun setOnboarded(onBoarded: Boolean) {}
    override fun isOnboarded(): Flow<Boolean> = flow {
        emit(true)
    }

    override suspend fun clear() {}
}