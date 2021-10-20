package com.example.kotlindemo.repository.local

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.kotlindemo.repository.model.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Tests [CoinsDb]
 */
class CoinsDbTest {
    private lateinit var dao: CoinsDao
    private lateinit var db: CoinsDb

    @Before
    fun createDb() {
        val application = ApplicationProvider.getApplicationContext() as Application
        db = Room.inMemoryDatabaseBuilder(application, CoinsDb::class.java).build()
        dao = db.coinsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testCoinsInsertAndDelete() = runBlocking {
        // Test insert
        dao.insertAllCoins(getCoins())
        var coins = dao.getAllCoins()
        assertEquals(1, coins.size)
        assertEquals("id1234", coins.first().id)
        assertEquals("Name", coins.first().name)
        assertEquals("nme", coins.first().symbol)
        assertEquals("type", coins.first().type)
        assertTrue(coins.first().isActive)
        assertFalse(coins.first().isNew)

        // Test delete
        dao.deleteAllCoins()
        coins = dao.getAllCoins()
        assertEquals(0, coins.size)
    }

    @Test
    @Throws(Exception::class)
    fun testCoinsDetailsInsertAndDelete() = runBlocking {
        // Test insert
        dao.insertCoinDetail(getCoinDetails())
        var coinDetails = dao.getCoinDetails("id1234")
        assertNotNull(coinDetails)
        coinDetails?.let { verifyCoinDetails(it) }


        // Test delete
        dao.deleteCoinDetails("id1234")
        coinDetails = dao.getCoinDetails("id1234")
        assertNull(coinDetails)
        assertNull(coinDetails)

        // Test delete all
        dao.insertCoinDetail(getCoinDetails())
        dao.deleteAllCoinDetails()
        coinDetails = dao.getCoinDetails("id1234")
        assertNull(coinDetails)
    }

    @Test
    @Throws(Exception::class)
    fun testTimeStampsInsertAndDelete() = runBlocking {
        // Test insert
        dao.insertTimeStamps(DataTimeStamps(coins = 1234))
        var timestamps = dao.getTimeStamps()
        assertNotNull(timestamps)
        assertEquals(1234L, timestamps?.coins)


        // Test delete
        dao.deleteTimeStamps()
        timestamps = dao.getTimeStamps()
        assertNull(timestamps)
    }

    private fun getCoins(): Array<Coin> {
        return arrayOf(
            Coin("id1234", isActive = true, isNew = false, name = "Name", rank = 1, symbol = "nme", type = "type")
        )
    }

    private fun verifyCoinDetails(coinDetails: CoinDetail) {
        assertEquals("description", coinDetails.description)
        assertEquals("development_status", coinDetails.development_status)
        assertEquals("first_data_at", coinDetails.first_data_at)
        assertEquals(true, coinDetails.hardware_wallet)
        assertEquals("hash_algorithm", coinDetails.hash_algorithm)
        assertEquals("id1234", coinDetails.id)
        assertEquals(true, coinDetails.is_active)
        assertEquals(true, coinDetails.is_new)
        assertEquals("last_data_at", coinDetails.last_data_at)
        assertEquals("youtube", coinDetails.links?.youtube?.get(0))
        var linkExtended = coinDetails.links_extended?.get(0)
        assertEquals("www.url.com", linkExtended?.url)
        assertEquals(5, linkExtended?.stats?.contributors)
        assertEquals(10, linkExtended?.stats?.followers)
        assertEquals("message", coinDetails.message)
        assertEquals("name", coinDetails.name)
        assertEquals(true, coinDetails.open_source)
        assertEquals("org_structure", coinDetails.org_structure)
        assertEquals("proof_type", coinDetails.proof_type)
        assertEquals(1, coinDetails.rank)
        assertEquals("started_at", coinDetails.started_at)
        assertEquals("symbol", coinDetails.symbol)
        assertEquals("Tag", coinDetails.tags?.get(0)?.name)
        assertEquals("Team", coinDetails.team?.get(0)?.name)
        assertEquals("type", coinDetails.type)
        assertEquals("link", coinDetails.whitepaper?.link)
    }

    private fun getCoinDetails(): CoinDetail = CoinDetail(
        description = "description",
        development_status = "development_status",
        first_data_at = "first_data_at",
        hardware_wallet = true,
        hash_algorithm = "hash_algorithm",
        id = "id1234",
        is_active = true,
        is_new = true,
        last_data_at = "last_data_at",
        links = Links(youtube = listOf("youtube")),
        links_extended = listOf(LinksExtended(stats = Stats(contributors = 5, followers = 10), url = "www.url.com")),
        message = "message",
        name = "name",
        open_source = true,
        org_structure = "org_structure",
        proof_type = "proof_type",
        rank = 1,
        started_at = "started_at",
        symbol = "symbol",
        tags = listOf(Tag(name = "Tag")),
        team = listOf(Team(name = "Team")),
        type = "type",
        whitepaper = Whitepaper(link = "link"),
    )
}