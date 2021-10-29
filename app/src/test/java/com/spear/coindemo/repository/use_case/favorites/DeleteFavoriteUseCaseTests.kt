package com.spear.coindemo.repository.use_case.favorites

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
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
class DeleteFavoriteUseCaseTests {

    @RelaxedMockK
    lateinit var mockCoinRepository: CoinRepository

    lateinit var subject: DeleteFavoriteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = DeleteFavoriteUseCase(mockCoinRepository)
    }

    @Test
    fun `Test Invoke with valid repository response`() = runBlocking {
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertNotNull(result[1].data)
        assertEquals(true, result[1].data)
        verify { mockCoinRepository.deleteFavorite("id1234") }
    }

    @Test
    fun `Test Invoke with exception`() = runBlocking {
        coEvery { mockCoinRepository.deleteFavorite(any()) } throws IOException()
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }
}