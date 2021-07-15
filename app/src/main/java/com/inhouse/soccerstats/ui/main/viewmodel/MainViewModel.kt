package com.inhouse.soccerstats.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inhouse.soccerstats.data.repository.MatchRepository
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) :
    ViewModel() {

    private val _matchesFlow = MutableStateFlow<State<List<Match>>>(State.loading())
    val matchesFlow: StateFlow<State<List<Match>>> = _matchesFlow

    fun getAllMatches() {
        viewModelScope.launch {
            matchRepository.getAllMatches()
                .onStart { _matchesFlow.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { _matchesFlow.value = it }
        }
    }
}