package com.spear.coindemo.repository.local

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPrefsImpl(private val context: Application) : AppPrefs {

    private val Context.dataStore by preferencesDataStore("app_preferences")

    override suspend fun setOnboarded() {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = true
        }
    }

    override fun isOnboarded(): Flow<Boolean> =
        context.dataStore.data.map { preferences -> preferences[ONBOARDING_KEY] ?: false }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

    companion object {
        private val ONBOARDING_KEY = booleanPreferencesKey("onboarding")
    }
}