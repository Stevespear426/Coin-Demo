package com.spear.coindemo.repository.local

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.spear.coindemo.repository.model.*
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
            Coin(
                "id1234",
                isActive = true,
                isNew = false,
                name = "Name",
                rank = 1,
                symbol = "nme",
                type = "type"
            )
        )
    }

    private fun getCoinDetails(): CoinDetail = CoinDetail(
        description = "description",
        developmentStatus = "development_status",
        firstDataAt = "first_data_at",
        hardwareWallet = true,
        hashAlgorithm = "hash_algorithm",
        id = "id1234",
        isActive = true,
        isNew = true,
        lastDataAt = "last_data_at",
        links = Links(
            explorer = listOf("explorer"),
            facebook = listOf("facebook"),
            reddit = listOf("reddit"),
            sourceCode = listOf("source_code"),
            website = listOf("website"),
            youtube = listOf("youtube")
        ),
        linksExtended = listOf(
            LinksExtended(
                stats = Stats(
                    contributors = 5,
                    followers = 10,
                    stars = 15,
                    subscribers = 20
                ), type = "type", url = "www.url.com"
            )
        ),
        message = "message",
        name = "name",
        openSource = true,
        orgStructure = "org_structure",
        proofType = "proof_type",
        rank = 1,
        startedAt = "started_at",
        symbol = "symbol",
        tags = listOf(Tag(id = "tagId", name = "tagName", coinCounter = 1, icoCounter = 5)),
        team = listOf(Team(id = "teamId", name = "teamName", position = "teamPosition")),
        type = "type",
        whitepaper = Whitepaper(link = "link", thumbnail = "thumbnail"),
    )

    private fun verifyCoinDetails(coinDetails: CoinDetail) {
        assertEquals("description", coinDetails.description)
        assertEquals("development_status", coinDetails.developmentStatus)
        assertEquals("first_data_at", coinDetails.firstDataAt)
        assertEquals(true, coinDetails.hardwareWallet)
        assertEquals("hash_algorithm", coinDetails.hashAlgorithm)
        assertEquals("id1234", coinDetails.id)
        assertEquals(true, coinDetails.isActive)
        assertEquals(true, coinDetails.isNew)
        assertEquals("last_data_at", coinDetails.lastDataAt)
        coinDetails.links?.let { verifyLinks(it) } ?: fail()
        coinDetails.linksExtended?.let { verifyLinksExtended(it) } ?: fail()
        assertEquals("message", coinDetails.message)
        assertEquals("name", coinDetails.name)
        assertEquals(true, coinDetails.openSource)
        assertEquals("org_structure", coinDetails.orgStructure)
        assertEquals("proof_type", coinDetails.proofType)
        assertEquals(1, coinDetails.rank)
        assertEquals("started_at", coinDetails.startedAt)
        assertEquals("symbol", coinDetails.symbol)
        coinDetails.tags?.let { verifyTags(it) } ?: fail()
        coinDetails.team?.let { verifyTeams(it) } ?: fail()
        assertEquals("type", coinDetails.type)
        assertEquals("link", coinDetails.whitepaper?.link)
        assertEquals("thumbnail", coinDetails.whitepaper?.thumbnail)
    }

    private fun verifyTeams(teams: List<Team>) {
        assertEquals("teamName", teams[0].name)
        assertEquals("teamId", teams[0].id)
        assertEquals("teamPosition", teams[0].position)
    }

    private fun verifyTags(tags: List<Tag>) {
        assertEquals("tagId", tags[0].id)
        assertEquals("tagName", tags[0].name)
        assertEquals(5, tags[0].icoCounter)
        assertEquals(1, tags[0].coinCounter)
    }

    private fun verifyLinksExtended(extended: List<LinksExtended>) {
        val linkExtended = extended[0]
        assertEquals("www.url.com", linkExtended.url)
        assertEquals("type", linkExtended.type)
        assertEquals(5, linkExtended.stats.contributors)
        assertEquals(10, linkExtended.stats.followers)
        assertEquals(15, linkExtended.stats.stars)
        assertEquals(20, linkExtended.stats.subscribers)
    }

    private fun verifyLinks(links: Links) {
        assertEquals("explorer", links.explorer[0])
        assertEquals("facebook", links.facebook[0])
        assertEquals("reddit", links.reddit[0])
        assertEquals("source_code", links.sourceCode[0])
        assertEquals("website", links.website[0])
        assertEquals("youtube", links.youtube[0])
    }
}