package com.inhouse.soccerstats.utils

import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.NetworkMatch

fun networkModelToDatabaseModelList(listNetworkMatches: List<NetworkMatch>): List<Match> =
    listNetworkMatches.map {
        Match(
            id = it.id,
            teamA = it.teamA,
            teamB = it.teamB,
            score = it.score,
            linkA = it.linkA,
            linkB = it.linkB,
            date = it.date
        )
    }
