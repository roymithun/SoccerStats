package com.inhouse.soccerstats.data.remote.api

import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch
import retrofit2.Response
import retrofit2.http.GET

interface SoccerMatchService {
    @GET(value = "9febbce8-92bb-4f01-85e3-c85c453b4924")
    suspend fun fetchMatchListA(): Response<List<NetworkMatch>>

    @GET(value = "2b0e7230-a048-4129-b08e-350b7a50fa83")
    suspend fun fetchMatchListB(): Response<List<NetworkMatch>>
}