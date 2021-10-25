package com.example.kotlindemo.repository

import com.example.kotlindemo.repository.local.AppPrefs
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Tests [AppRepository]
 */
class AppRepositoryTests {

    @RelaxedMockK
    lateinit var mockAppPrefs: AppPrefs

    private lateinit var subject: AppRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = AppRepository(mockAppPrefs)
    }

    @Test
    fun `Test isOnboarded`() = runBlocking {
        subject.isOnboarded()
        coVerify { mockAppPrefs.isOnboarded() }
    }

    @Test
    fun `Test setOnboarded`() = runBlocking {
        subject.setOnboarded()
        coVerify { mockAppPrefs.setOnboarded() }
    }
}