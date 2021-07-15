package com.inhouse.soccerstats.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inhouse.soccerstats.R
import com.inhouse.soccerstats.model.State
import com.inhouse.soccerstats.ui.main.adapter.MatchAdapter
import com.inhouse.soccerstats.ui.main.viewholder.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: MatchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val matchListView = view.findViewById<RecyclerView>(R.id.rv_match_list)

        adapter = MatchAdapter()
        matchListView.layoutManager = LinearLayoutManager(requireContext())
        matchListView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.matchesFlow.collect { state ->
                when (state) {
                    is State.Loading -> println("loading state")
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            println("success response ${state.data.toList()}")
                            adapter.submitList(state.data.toMutableList())
                        }
                    }
                    is State.Error -> {
                        println("error response ${state.message}")
                    }
                }
            }
        }
    }
}