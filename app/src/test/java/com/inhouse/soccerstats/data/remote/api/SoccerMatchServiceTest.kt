package com.inhouse.soccerstats.data.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch
import com.inhouse.soccerstats.utils.CoroutineTestRule
import com.inhouse.soccerstats.utils.MockResponseFileReader
import com.inhouse.soccerstats.utils.SUCCESS_RESPONSE_FILENAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class SoccerMatchServiceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var mockWebServer = MockWebServer()
    private lateinit var soccerMatchService: SoccerMatchService

    @Before
    fun setUp() {
        mockWebServer.start()
        soccerMatchService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(SoccerMatchService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch match list successful and first team matches mock response`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(SUCCESS_RESPONSE_FILENAME).content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val matchListResponse: Response<List<NetworkMatch>> = soccerMatchService.fetchMatchListA()
            assertThat(matchListResponse.isSuccessful).isTrue()
            assertThat(matchListResponse.body()?.first()?.teamA).isEqualTo("FC Barcelona")
        }
    }
}