package com.inhouse.soccerstats.model

import android.os.Parcelable
import com.inhouse.soccerstats.utils.dateOnlyFormat
import com.inhouse.soccerstats.utils.longDateFormat
import com.inhouse.soccerstats.utils.timeOnlyFormat
import com.squareup.moshi.Json
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Match(
    @Json(name = "Team_A") val teamA: String,
    @Json(name = "Team_B") val teamB: String,
    @Json(name = "Score") val score: String,
    @Json(name = "link_A") val linkA: String,
    @Json(name = "link_B") val linkB: String,
    @Json(name = "Date") val date: String
) : Parcelable {
    @IgnoredOnParcel
    private val matchDateTime: Date
        get() = longDateFormat().parse(date) ?: Date()

    val matchDate: String
        get() = dateOnlyFormat().format(matchDateTime)

    val matchTime: String
        get() = timeOnlyFormat().format(matchDateTime)
}