package com.inhouse.soccerstats.data.repository

import com.inhouse.soccerstats.data.local.SoccerMatchDao
import com.inhouse.soccerstats.data.remote.api.SoccerMatchService
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch
import com.inhouse.soccerstats.utils.networkModelToDatabaseModelList
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMatchRepository @Inject constructor(
    private val soccerMatchDao: SoccerMatchDao,
    private val soccerMatchService: SoccerMatchService
) :
    MatchRepository {
    override fun getAllMatches() = flow {
        // Emit Database content first
        emit(Resource.Success(soccerMatchDao.getAllMatches().first()))

        // Remote Datasource handling
        val responseMatchesA = soccerMatchService.fetchMatchListA()
        val matchesA = responseMatchesA.body()
        if (!responseMatchesA.isSuccessful or (matchesA == null)) {
            emit(Resource.Failed(responseMatchesA.message()))
        }
        val responseMatchesB = soccerMatchService.fetchMatchListB()
        val matchesB = responseMatchesB.body()
        if (!responseMatchesB.isSuccessful or (matchesB == null)) {
            emit(Resource.Failed(responseMatchesB.message()))
        }

        val resultA: List<NetworkMatch> = matchesA ?: emptyList()
        val resultB: List<NetworkMatch> = matchesB ?: emptyList()

        val result = resultA + resultB
        soccerMatchDao.insertAllMatches(networkModelToDatabaseModelList(result))

        // fetch all matches from room persistence storage and emit
        emitAll(
            soccerMatchDao.getAllMatches().map {
                Resource.Success(it)
            }
        )
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error! Try again later"))
    }

    override fun getMatchById(matchId: Int): Flow<Match> = soccerMatchDao.getMatchById(matchId)
}