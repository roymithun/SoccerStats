package com.inhouse.soccerstats.data.local

import com.inhouse.soccerstats.model.Match

object FakeSoccerMatchList {
    val matches = listOf(
        Match(
            id = 1,
            teamA = "FC Barcelona",
            teamB = "Chelsea",
            score = "2-0",
            linkA = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            linkB = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            date = "25 July 2021 21:00"
        ),
        Match(
            id = 2,
            teamA = "Manchester United",
            teamB = "Liverpool F.C",
            score = "1-1",
            linkA = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            linkB = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            date = "25 July 2021 21:00"
        ),
        Match(
            id = 3,
            teamA = "Leeds United",
            teamB = "Arsenal F.C",
            score = "2-3",
            linkA = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            linkB = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
            date = "25 July 2021 21:00"
        )
    )
}