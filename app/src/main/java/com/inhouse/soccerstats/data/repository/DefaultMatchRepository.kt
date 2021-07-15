package com.inhouse.soccerstats.data.repository

import com.inhouse.soccerstats.data.remote.api.SoccerMatchService
import com.inhouse.soccerstats.model.Match
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchRepository @Inject constructor(
    private val matchService: SoccerMatchService
) :
    MatchRepository {
    override fun getAllMatches() = flow<Resource<List<Match>>> {
        // Emit Database content first
        emit(Resource.Success(emptyList()))
        val matchesA = matchService.fetchMatchListA()
        val matchesB = matchService.fetchMatchListB()
        val resultA: List<Match> =
            if (matchesA.isSuccessful) matchesA.body() ?: emptyList() else emptyList()
        val resultB = if (matchesB.isSuccessful) matchesB.body() ?: emptyList() else emptyList()

        val result = resultA + resultB

        emit(Resource.Success(result))
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error! Try again later"))
    }
}