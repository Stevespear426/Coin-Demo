package com.spear.coindemo.repository.local

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.spear.coindemo.repository.model.*
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
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
        verifyCoin(coins.first())

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
    fun testFavoritesInsertAndDelete() = runBlocking {
        // Test insert
        dao.insertFavorite(Favorite("id"))
        var favorites = dao.getFavoritesFlow()
        assertNotNull(favorites)
        assertEquals(1, favorites.take(1).toList()[0].size)
        assertEquals("id", favorites.take(1).toList()[0][0].id)

        // Test delete
        dao.deleteFavorite("id")
        favorites = dao.getFavoritesFlow()
        assertNotNull(favorites)
        assertEquals(0, favorites.take(1).toList()[0].size)
    }

    private fun getCoins(): Array<Coin> {
        return arrayOf(
            Coin(
                "id1234",
                name = "Name",
                rank = 1,
                symbol = "nme",
                betaValue = 0.01,
                circulatingSupply = 12345,
                firstDataAt = "2021-10-29T16:23:06Z",
                lastUpdated = "2021-10-29T16:23:06Z",
                maxSupply = 34567,
                totalSupply = 56789,
                quotes = Quotes(
                    USD(
                        price = 0.00,
                        athDate = "2021-10-29T16:23:06Z",
                        athPrice = 0.01,
                        marketCap = 1234567890,
                        marketCapChange24h = 0.02,
                        change15m = 0.03,
                        change30m = 0.04,
                        change1h = 0.05,
                        change6h = 0.06,
                        change12h = 0.07,
                        change24h = 0.08,
                        change7d = 0.09,
                        change30d = 0.10,
                        change1y = 0.11,
                        changeTotal = 0.12,
                        volume24h = 0.13,
                        volume24hChange24h = 0.14
                    )
                )
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

    private fun verifyCoin(coin: Coin) {
        assertEquals("id1234", coin.id)
        assertEquals("Name", coin.name)
        assertEquals(1, coin.rank)
        assertEquals("nme", coin.symbol)
        assertEquals(0.01, coin.betaValue)
        assertEquals(12345L, coin.circulatingSupply)
        assertEquals(56789L, coin.totalSupply)
        assertEquals(34567L, coin.maxSupply)
        assertEquals("2021-10-29T16:23:06Z", coin.firstDataAt)
        assertEquals("2021-10-29T16:23:06Z", coin.lastUpdated)
        verifyQuotes(coin.quotes)
    }

    private fun verifyQuotes(quotes: Quotes?) {
        assertNotNull(quotes?.USD)
        quotes?.USD?.let { usd ->
            assertEquals(0.00, usd.price)
            assertEquals("2021-10-29T16:23:06Z", usd.athDate)
            assertEquals(0.01, usd.athPrice)
            assertEquals(1234567890L, usd.marketCap)
            assertEquals(0.02, usd.marketCapChange24h)
            assertEquals(0.03, usd.change15m)
            assertEquals(0.04, usd.change30m)
            assertEquals(0.05, usd.change1h)
            assertEquals(0.06, usd.change6h)
            assertEquals(0.07, usd.change12h)
            assertEquals(0.08, usd.change24h)
            assertEquals(0.09, usd.change7d)
            assertEquals(0.10, usd.change30d)
            assertEquals(0.11, usd.change1y)
            assertEquals(0.12, usd.changeTotal)
            assertEquals(0.13, usd.volume24h)
            assertEquals(0.14, usd.volume24hChange24h)
        }
    }

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