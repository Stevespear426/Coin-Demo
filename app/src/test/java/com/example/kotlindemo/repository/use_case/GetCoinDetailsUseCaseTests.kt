package com.example.kotlindemo.repository.use_case

import com.example.kotlindemo.common.Resource
import com.example.kotlindemo.repository.CoinRepository
import com.example.kotlindemo.repository.model.CoinDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Tests [GetCoinsUseCase]
 */
class GetCoinDetailsUseCaseTests {

    @MockK
    lateinit var mockCoinRepository: CoinRepository

    lateinit var subject: GetCoinDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = GetCoinDetailUseCase(mockCoinRepository)
    }

    @Test
    fun `Test Invoke with valid repository response`() = runBlocking {
        val mockCoinDetails = mockk<CoinDetail>(relaxed = true)
        coEvery { mockCoinRepository.getCoinDetails(any()) } returns mockCoinDetails
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertNotNull(result[1].data)
        assertEquals(mockCoinDetails, result[1].data)
    }

    @Test
    fun `Test Invoke with exception`() = runBlocking {
        coEvery { mockCoinRepository.getCoinDetails(any()) } throws IOException()
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }
}