package com.example.kotlindemo.repository.use_case

import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.CoinRepository
import com.example.kotlindemo.repository.model.Coin
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Tests [GetCoinsUseCase]
 */
class GetCoinsUseCaseTests {

    lateinit var subject: GetCoinsUseCase

    @MockK
    lateinit var mockCoinRepository: CoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = GetCoinsUseCase(mockCoinRepository)
    }

    @Test
    fun `Test Invoke with valid repository response`() = runBlocking {
        val mockCoins = mockk<List<Coin>>(relaxed = true)
        coEvery { mockCoinRepository.getCoins() } returns mockCoins
        val result = subject().take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(mockCoins, result[1].data)
    }

    @Test
    fun `Test Invoke with exception`() = runBlocking {
        coEvery { mockCoinRepository.getCoins() } throws IOException()
        val result = subject().take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }
}