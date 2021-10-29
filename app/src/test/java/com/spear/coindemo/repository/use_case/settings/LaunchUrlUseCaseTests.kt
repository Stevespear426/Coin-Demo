package com.spear.coindemo.repository.use_case.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Tests [GetCoinsUseCase]
 */
class LaunchUrlUseCaseTests {

    companion object {
        const val URL = "www.google.com"
    }

    lateinit var subject: LaunchUrlUseCase

    @MockK
    lateinit var mockContext: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockkStatic(Uri::class)
        every { Uri.parse("www.google.com") } returns Uri.EMPTY
        mockkConstructor(Intent::class)
        every { anyConstructed<Intent>().setData(any()) } returns mockk(relaxed = true)
        subject = LaunchUrlUseCase()
    }

    @Test
    fun `Test Invoke`() = runBlocking {
        subject(mockContext, URL)
        verify { mockContext.startActivity(any()) }
    }
}