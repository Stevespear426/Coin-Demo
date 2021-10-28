package com.spear.coindemo.repository

import com.spear.coindemo.repository.local.AppPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(val appPrefs: AppPrefs) {

    suspend fun setOnboarded() {
        appPrefs.setOnboarded()
    }

    fun isOnboarded(): Flow<Boolean> = appPrefs.isOnboarded()
}