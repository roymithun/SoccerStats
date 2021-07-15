package com.inhouse.soccerstats.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.inhouse.soccerstats.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) :
    ViewModel() {
    fun getMatch(matchId: Int) = matchRepository.getMatchById(matchId)
}
