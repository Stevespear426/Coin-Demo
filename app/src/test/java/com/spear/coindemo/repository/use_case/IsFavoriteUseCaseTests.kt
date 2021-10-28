package com.spear.coindemo.repository.use_case

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.Favorite
import com.spear.coindemo.repository.use_case.favorites.IsFavoriteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
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
class IsFavoriteUseCaseTests {

    lateinit var subject: IsFavoriteUseCase

    @MockK
    lateinit var mockCoinRepository: CoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = IsFavoriteUseCase(mockCoinRepository)
    }

    @Test
    fun `Test Invoke with valid favorites`() = runBlocking {
        val favorites = listOf(Favorite("id1234"))
        coEvery { mockCoinRepository.getFavorites() } returns flow { emit(favorites) }
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(true, result[1].data)
    }

    @Test
    fun `Test Invoke with invalid favorites`() = runBlocking {
        val favorites = listOf(Favorite("id1234"))
        coEvery { mockCoinRepository.getFavorites() } returns flow { emit(favorites) }
        val result = subject("not valid id").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(false, result[1].data)
    }

    @Test
    fun `Test Invoke with exception`() = runBlocking {
        coEvery { mockCoinRepository.getFavorites() } throws IOException()
        val result = subject("id1234").take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }
}