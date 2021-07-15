package com.inhouse.soccerstats.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inhouse.soccerstats.model.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface SoccerMatchDao {
    /**
     * Inserts [matches] into the [Match.TABLE_NAME] table.
     * @param matches List of Matches
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatches(matches: List<Match>)

    /**
     * Fetches all the matches from the [Match.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Match.TABLE_NAME}")
    fun getAllMatches(): Flow<List<Match>>

    @Query("SELECT * FROM ${Match.TABLE_NAME} WHERE ID = :matchId")
    fun getMatchById(matchId: Int): Flow<Match>
}