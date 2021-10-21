package com.example.kotlindemo.repository.remote

import com.example.kotlindemo.repository.model.*
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class CoinServiceTests {

    private var mockWebServer = MockWebServer()
    lateinit var subject: CoinService

    @Before
    fun setup() {
        mockWebServer.start()
        subject = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Test Get Coins`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResponseData.COINS)
        mockWebServer.enqueue(response)

        val coins = subject.getCoins()
        assertNotNull(coins)
        assertEquals(9, coins.size)

        val coin = coins.first()
        assertEquals("btc-bitcoin", coin.id)
        assertEquals(true, coin.isActive)
        assertEquals(false, coin.isNew)
        assertEquals("Bitcoin", coin.name)
        assertEquals(1, coin.rank)
        assertEquals("BTC", coin.symbol)
        assertEquals("coin", coin.type)
    }

    @Test
    fun `Test Get CoinDetails`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResponseData.COIN_DETAILS)
        mockWebServer.enqueue(response)

        val coinDetails = subject.getCoinDetails("id")
        assertNotNull(coinDetails)
        verifyCoinDetails(coinDetails)

    }

    private fun verifyCoinDetails(coinDetails: CoinDetail) {
        assertEquals("Bitcoin is a cryptocurrency and worldwide payment system.", coinDetails.description)
        assertEquals("Working product", coinDetails.developmentStatus)
        assertEquals("2010-07-17T00:00:00Z", coinDetails.firstDataAt)
        assertEquals(true, coinDetails.hardwareWallet)
        assertEquals("SHA256", coinDetails.hashAlgorithm)
        assertEquals("btc-bitcoin", coinDetails.id)
        assertEquals(true, coinDetails.isActive)
        assertEquals(false, coinDetails.isNew)
        assertEquals("2021-10-19T20:25:00Z", coinDetails.lastDataAt)
        coinDetails.links?.let { verifyLinks(it) }?: fail()
        coinDetails.linksExtended?.let { verifyLinksExtended(it) }?: fail()
        assertEquals("", coinDetails.message)
        assertEquals("Bitcoin", coinDetails.name)
        assertEquals(true, coinDetails.openSource)
        assertEquals("Decentralized", coinDetails.orgStructure)
        assertEquals("Proof of Work", coinDetails.proofType)
        assertEquals(1, coinDetails.rank)
        assertEquals("2009-01-03T00:00:00Z", coinDetails.startedAt)
        assertEquals("BTC", coinDetails.symbol)
        coinDetails.tags?.let { verifyTags(it) }?: fail()
        coinDetails.team?.let { verifyTeam(it) }?: fail()
        assertEquals("coin", coinDetails.type)
        coinDetails.whitepaper?.let { verifyWhitepaper(it) }?: fail()
    }

    private fun verifyWhitepaper(whitepaper: Whitepaper) {
        assertEquals("https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf", whitepaper.link)
        assertEquals("https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg", whitepaper.thumbnail)
    }

    private fun verifyTeam(team: List<Team>) {
        assertEquals("satoshi-nakamoto", team[0].id)
        assertEquals("Satoshi Nakamoto", team[0].name)
        assertEquals("Founder", team[0].position)
    }

    private fun verifyTags(tags: List<Tag>) {
        assertEquals("segwit", tags[0].id)
        assertEquals("Segwit", tags[0].name)
        assertEquals(1, tags[0].icoCounter)
        assertEquals(11, tags[0].coinCounter)
    }

    private fun verifyLinksExtended(extended: List<LinksExtended>) {
        assertEquals("https://bitcoin.org/en/blog", extended[0].url)
        assertEquals("blog", extended[0].type)
        val stats = extended[0].stats
        assertEquals(5, stats.contributors)
        assertEquals(10, stats.followers)
        assertEquals(15, stats.stars)
        assertEquals(20, stats.subscribers)
    }

    private fun verifyLinks(links: Links) {
        assertEquals("https://blockchain.com/explorer", links.explorer[1])
        assertEquals("https://www.facebook.com/bitcoins/", links.facebook[0])
        assertEquals("https://www.reddit.com/r/bitcoin", links.reddit[0])
        assertEquals("https://github.com/bitcoin/bitcoin", links.sourceCode[0])
        assertEquals("https://bitcoin.org/", links.website[0])
        assertEquals("https://www.youtube.com/watch?v=Gc2en3nHxA4&", links.youtube[0])

    }
}