package com.spear.coindemo.repository.use_case.favorites

import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.CoinRepository
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.Favorite
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
class GetFavoriteCoinsUseCaseTests {

    lateinit var subject: GetFavoriteCoinsUseCase

    @MockK
    lateinit var mockCoinRepository: CoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = GetFavoriteCoinsUseCase(mockCoinRepository)
    }

    @Test
    fun `Test Invoke with valid repository response`() = runBlocking {
        val coin = getCoin()
        val coins = listOf(coin, coin.copy(id = "id4567"))
        val favorites = listOf(Favorite("id1234"))
        coEvery { mockCoinRepository.getCoins() } returns coins
        coEvery { mockCoinRepository.getFavorites() } returns flow { emit(favorites) }
        val result = subject().take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(1, result[1].data?.size)
        assertEquals("id1234", result[1].data?.get(0)?.id)
    }

    @Test
    fun `Test Favorites Flow Update`() = runBlocking {
        val coin = getCoin()
        val coins = listOf(coin, coin.copy(id = "id4567"))
        val favorites = listOf(Favorite("id1234"), Favorite("id4567"))
        coEvery { mockCoinRepository.getCoins() } returns coins

        val favoritesFlow = flow {
            emit(favorites)
            emit(listOf(Favorite("id1234")))
        }
        coEvery { mockCoinRepository.getFavorites() } returns favoritesFlow
        val result = subject().take(3).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals(2, result[1].data?.size)

        assertTrue(result[2] is Resource.Success)
        assertEquals(1, result[2].data?.size)
    }

    @Test
    fun `Test Invoke with exception`() = runBlocking {
        coEvery { mockCoinRepository.getCoins() } throws IOException()
        val result = subject().take(2).toList()
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }

    private fun getCoin(): Coin = Coin(
        id = "id1234",
        name = "name",
        rank = 1,
        symbol = "symbol",
    )
}