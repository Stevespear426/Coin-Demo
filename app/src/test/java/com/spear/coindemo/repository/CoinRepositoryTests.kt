package com.spear.coindemo.repository

import com.spear.coindemo.repository.local.CoinsDao
import com.spear.coindemo.repository.model.*
import com.spear.coindemo.repository.remote.CoinService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests [CoinRepository]
 */
class CoinRepositoryTests {

    @MockK
    lateinit var mockCoinService: CoinService

    @MockK
    lateinit var mockCoinsDao: CoinsDao

    lateinit var subject: CoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        subject = CoinRepository(mockCoinService, mockCoinsDao)
    }

    @Test
    fun `Test has no coins in DB`() = runBlocking {
        coEvery { mockCoinsDao.getAllCoins() } returns emptyList()
        coEvery { mockCoinService.getCoins() } returns getCoins("Name")
        val mockTimeStamps = mockk<DataTimeStamps>(relaxed = true)
        every { mockTimeStamps.coinsExpired() } returns false
        coEvery { mockCoinsDao.getTimeStamps() } returns mockTimeStamps
        val result = subject.getCoins()

        coVerify { mockCoinService.getCoins() }
        coVerify { mockCoinsDao.insertAllCoins(any()) }
        verify { mockTimeStamps.updateCoinTimestamp() }
        coVerify { mockCoinsDao.insertTimeStamps(any()) }

        assertEquals(1, result.size)
    }

    @Test
    fun `Test has expired coins in DB`() = runBlocking {
        coEvery { mockCoinsDao.getAllCoins() } returns getCoins("Old")
        coEvery { mockCoinService.getCoins() } returns getCoins("New")
        val mockTimeStamps = mockk<DataTimeStamps>(relaxed = true)
        every { mockTimeStamps.coinsExpired() } returns true
        coEvery { mockCoinsDao.getTimeStamps() } returns mockTimeStamps
        val result = subject.getCoins()

        coVerify { mockCoinService.getCoins() }
        coVerify { mockCoinsDao.insertAllCoins(any()) }
        verify { mockTimeStamps.updateCoinTimestamp() }
        coVerify { mockCoinsDao.insertTimeStamps(any()) }

        assertEquals(1, result.size)
        assertEquals("New", result.first().name)
    }

    @Test
    fun `Test has valid coins in DB`() = runBlocking {
        coEvery { mockCoinsDao.getAllCoins() } returns getCoins("Name")
        val mockTimeStamps = mockkClass(DataTimeStamps::class)
        every { mockTimeStamps.coinsExpired() } returns false
        coEvery { mockCoinsDao.getTimeStamps() } returns mockTimeStamps
        val result = subject.getCoins()

        coVerify(exactly = 0) { mockCoinService.getCoins() }
        assertEquals(1, result.size)
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

    private fun getCoins(name: String): List<Coin> {
        return listOf(
            Coin(
                "id1234",
                isActive = true,
                isNew = false,
                name = name,
                rank = 1,
                symbol = "nme",
                type = "type"
            )
        )
    }

    private fun getCoinDetails(): CoinDetail = CoinDetail(
        description = "",
        developmentStatus = "",
        firstDataAt = "",
        hardwareWallet = true,
        hashAlgorithm = "",
        id = "id1234",
        isActive = true,
        isNew = true,
        lastDataAt = "",
        links = Links(),
        linksExtended = emptyList(),
        message = "",
        name = "",
        openSource = true,
        orgStructure = "",
        proofType = "",
        rank = 1,
        startedAt = "",
        symbol = "",
        tags = emptyList(),
        team = emptyList(),
        type = "",
        whitepaper = Whitepaper(),
    )
}