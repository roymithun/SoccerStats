package com.inhouse.soccerstats.data.remote.api

import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch
import retrofit2.Response
import retrofit2.http.GET

interface SoccerMatchService {
    @GET(value = "23745f3a-5eaa-43cf-ab46-737eb273596b")
    suspend fun fetchMatchListA(): Response<List<NetworkMatch>>

    @GET(value = "bc1ce3b7-6ad2-4fef-af6c-08f8865b210e")
    suspend fun fetchMatchListB(): Response<List<NetworkMatch>>
}