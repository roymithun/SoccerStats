package com.inhouse.soccerstats.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inhouse.soccerstats.model.Match.Companion.TABLE_NAME
import com.inhouse.soccerstats.utils.dateOnlyFormat
import com.inhouse.soccerstats.utils.longDateFormat
import com.inhouse.soccerstats.utils.timeOnlyFormat
import java.util.*

@Entity(tableName = TABLE_NAME)
data class Match(
    @PrimaryKey val id: Int,
    val teamA: String,
    val teamB: String,
    val score: String,
    val linkA: String,
    val linkB: String,
    val date: String
) {
    companion object {
        const val TABLE_NAME = "matches"
    }
}

fun Match.matchDateTime(): Date = longDateFormat().parse(date) ?: Date()
fun Match.matchDate(): String = dateOnlyFormat().format(matchDateTime())
fun Match.matchTime(): String = timeOnlyFormat().format(matchDateTime())
fun Match.scoreSplit(): Pair<String, String> = score.split("-").let { Pair(it.first(), it.last()) }
