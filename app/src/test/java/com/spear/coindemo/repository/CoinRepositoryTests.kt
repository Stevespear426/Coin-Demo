package com.spear.coindemo.repository

import com.spear.coindemo.repository.local.CoinsDao
import com.spear.coindemo.repository.model.Coin
import com.spear.coindemo.repository.model.CoinDetail
import com.spear.coindemo.repository.remote.CoinService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Tests [CoinRepository]
 */
class CoinRepositoryTests {

    @MockK
    lateinit var mockCoinService: CoinService

    @RelaxedMockK
    lateinit var mockCoinsDao: CoinsDao

    lateinit var subject: CoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = CoinRepository(mockCoinService, mockCoinsDao)
    }

    @Test
    fun `Test Successful Retrofit Fetch`() = runBlocking {
        coEvery { mockCoinService.getCoins() } returns getCoins("Name")
        val result = subject.getCoins()

        coVerify { mockCoinService.getCoins() }
        coVerify { mockCoinsDao.insertAllCoins(any()) }

        assertEquals(1, result.size)

        coVerify(exactly = 0) { mockCoinsDao.getAllCoins() }

    }

    @Test
    fun `Test Retrofit Fetch Failure`() = runBlocking {
        coEvery { mockCoinsDao.getAllCoins() } returns getCoins("Name")
        coEvery { mockCoinService.getCoins() } throws Exception()
        val result = subject.getCoins()

        coVerify { mockCoinService.getCoins() }
        coVerify(exactly = 0) { mockCoinsDao.insertAllCoins(any()) }
        coVerify { mockCoinsDao.getAllCoins() }

        assertEquals(1, result.size)

    }

    @Test
    fun `Test Retrofit Fetch Failure and Empty DB`() = runBlocking {
        coEvery { mockCoinsDao.getAllCoins() } returns emptyList()
        coEvery { mockCoinService.getCoins() } throws Exception()
        val result = subject.getCoins()

        coVerify { mockCoinService.getCoins() }
        coVerify(exactly = 0) { mockCoinsDao.insertAllCoins(any()) }
        coVerify { mockCoinsDao.getAllCoins() }

        assertEquals(0, result.size)

    }


    @Test
    fun `Test has no coinDetail in DB`() = runBlocking {
        coEvery { mockCoinsDao.getCoinDetails(any()) } returns null
        val mockCoinDetail = mockk<CoinDetail>(relaxed = true)
        coEvery { mockCoinService.getCoinDetails(any()) } returns mockCoinDetail
        val result = subject.getCoinDetails("id1234")

        coVerify { mockCoinService.getCoinDetails("id1234") }
        coVerify { mockCoinsDao.insertCoinDetail(mockCoinDetail) }
        verify { mockCoinDetail.updateTimestamp() }
        assertTrue(result is CoinDetail)
    }

    @Test
    fun `Test has expired coinDetail in DB`() = runBlocking {
        val mockCoinDetailOld = mockk<CoinDetail>(relaxed = true)
        every { mockCoinDetailOld.expired() } returns true
        coEvery { mockCoinsDao.getCoinDetails(any()) } returns mockCoinDetailOld

        val mockCoinDetailNew = mockk<CoinDetail>(relaxed = true)
        coEvery { mockCoinService.getCoinDetails(any()) } returns mockCoinDetailNew

        val result = subject.getCoinDetails("id1234")

        coVerify { mockCoinService.getCoinDetails("id1234") }
        coVerify { mockCoinsDao.insertCoinDetail(mockCoinDetailNew) }
        verify { mockCoinDetailNew.updateTimestamp() }
        assertEquals(mockCoinDetailNew, result)
    }

    @Test
    fun `Test has valid coinDetail in DB`() = runBlocking {
        val mockCoinDetail = mockk<CoinDetail>(relaxed = true)
        every { mockCoinDetail.expired() } returns false
        coEvery { mockCoinsDao.getCoinDetails(any()) } returns mockCoinDetail

        val result = subject.getCoinDetails("id1234")

        coVerify(exactly = 0) { mockCoinService.getCoinDetails(any()) }
        assertEquals(mockCoinDetail, result)
    }

    @Test
    fun `Test get Favorites`() = runBlocking {
        val result = subject.getFavorites()
        coVerify { mockCoinsDao.getFavoritesFlow() }
        assertNotNull(result)
    }

    private fun getCoins(name: String): List<Coin> {
        return listOf(
            Coin(
                "id1234",
                name = name,
                rank = 1,
                symbol = "nme",
            )
        )
    }
}