package com.inhouse.soccerstats.model

import com.squareup.moshi.Json

data class Match(
    @Json(name = "Team_A") val teamA: String,
    @Json(name = "Team_B") val teamB: String,
    @Json(name = "Score") val score: String,
    @Json(name = "link_A") val linkA: String,
    @Json(name = "link_B") val linkB: String,
    @Json(name = "Date") val date: String
)