package com.inhouse.soccerstats.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inhouse.soccerstats.model.Match

@Database(
    version = 1,
    entities = [Match::class],
    exportSchema = false
)
abstract class SoccerMatchDatabase : RoomDatabase() {
    abstract fun soccerMatchDao(): SoccerMatchDao
}