package com.example.kotlindemo.repository.local

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests [AppPrefs]
 */
class AppPrefsTests {
    private lateinit var appPrefs: AppPrefs

    @Before
    fun setup() = runBlocking {
        val application = ApplicationProvider.getApplicationContext() as Application
        appPrefs = AppPrefsImpl(application)
        appPrefs.clear()
    }

    @After
    fun teardown() = runBlocking {
        appPrefs.clear()
    }

    @Test
    fun testOnboarding() = runBlocking {
        var result = appPrefs.isOnboarded().take(1).toList().first()
        assertFalse(result)

        appPrefs.setOnboarded()

        result = appPrefs.isOnboarded().take(1).toList().first()
        assertTrue(result)
    }
}