package com.inhouse.soccerstats.data.repository

import com.inhouse.soccerstats.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getAllMatches(): Flow<Resource<List<Match>>>
    fun getMatchById(matchId: Int): Flow<Match>
}