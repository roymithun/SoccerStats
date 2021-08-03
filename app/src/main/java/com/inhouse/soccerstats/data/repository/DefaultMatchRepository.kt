package com.inhouse.soccerstats.data.repository

import android.util.Log
import com.inhouse.soccerstats.data.local.SoccerMatchDao
import com.inhouse.soccerstats.data.remote.api.SoccerMatchService
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch
import com.inhouse.soccerstats.utils.networkModelToDatabaseModelList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.system.measureTimeMillis

@Singleton
class DefaultMatchRepository @Inject constructor(
    private val soccerMatchDao: SoccerMatchDao,
    private val soccerMatchService: SoccerMatchService
) :
    MatchRepository {
    companion object {
        const val TAG = "DefaultMatchRepository"
    }

    override fun getAllMatches() = flow {
        // Emit Database content first
        emit(Resource.Success(soccerMatchDao.getAllMatches().first()))

        coroutineScope {
            val time = measureTimeMillis {
                // Remote Datasource handling
                val responseMatchesADeferred = async {
//                    Log.d(TAG, "fetchMatchListA async launched")
                    soccerMatchService.fetchMatchListA()
                }
                val responseMatchesBDeferred = async {
//                    Log.d(TAG, "fetchMatchListB async launched")
                    soccerMatchService.fetchMatchListB()
                }

                val responseMatchesA = responseMatchesADeferred.await()
                val responseMatchesB = responseMatchesBDeferred.await()

                val matchesA = responseMatchesA.body()
                if (!responseMatchesA.isSuccessful or (matchesA == null)) {
                    Log.d(
                        TAG,
                        "responseMatchesA is not successful  or (matchesA == null)"
                    )
                    emit(Resource.Failed(responseMatchesA.message()))
                }
                val matchesB = responseMatchesB.body()
                if (!responseMatchesB.isSuccessful or (matchesB == null)) {
                    Log.d(
                        TAG,
                        "responseMatchesB is not successful or (matchesB == null)"
                    )
                    emit(Resource.Failed(responseMatchesB.message()))
                }

                val resultA: List<NetworkMatch> = matchesA ?: emptyList()
                val resultB: List<NetworkMatch> = matchesB ?: emptyList()

                val result = resultA + resultB
//                Log.d(TAG, "result $result")

                soccerMatchDao.insertAllMatches(networkModelToDatabaseModelList(result))
            }
//            Log.d(TAG, "time = $time")

            // fetch all matches from room persistence storage and emit
            emitAll(
                soccerMatchDao.getAllMatches().map {
                    Resource.Success(it)
                }
            )
        }
    }.catch { e ->
        Log.d(TAG, "processing failed")
        e.printStackTrace()
        emit(Resource.Failed("Network error! Try again later"))
    }

    override fun getMatchById(matchId: Int): Flow<Match> = soccerMatchDao.getMatchById(matchId)
}