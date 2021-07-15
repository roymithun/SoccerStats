package com.inhouse.soccerstats.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.inhouse.soccerstats.R
import com.inhouse.soccerstats.databinding.FragmentMainBinding
import com.inhouse.soccerstats.model.State
import com.inhouse.soccerstats.ui.main.adapter.MatchAdapter
import com.inhouse.soccerstats.ui.main.viewholder.MainViewModel
import com.inhouse.soccerstats.utils.ANIMATION_DURATION
import com.inhouse.soccerstats.utils.NetworkConnectionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val mainViewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var networkConnectionUtils: NetworkConnectionUtils
    private lateinit var adapter: MatchAdapter
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MatchAdapter()
        binding.rvMatchList.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getAllMatches()
        }
        observeNetworkConnectivityEvents()
        // If current State isn't `Success` then query for all matches again
        mainViewModel.matchesFlow.value.let { currentState ->
            if (!currentState.isSuccessful()) {
                mainViewModel.getAllMatches()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.matchesFlow.collect { state ->
                when (state) {
                    is State.Loading -> {
                        showLoading(true)
                    }
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            adapter.submitList(state.data.toMutableList())
                            showLoading(false)
                        }
                    }
                    is State.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        showLoading(true)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun observeNetworkConnectivityEvents() {
        networkConnectionUtils.getNetworkLiveData().observe(viewLifecycleOwner) { isConnected ->
            if (!isConnected) {
                binding.tvNetworkStatus.text = getString(R.string.text_no_connectivity)
                binding.llNetworkStatus.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(getColorRes(R.color.network_error))
                }
            } else {
                if (mainViewModel.matchesFlow.value is State.Error || adapter.itemCount == 0) {
                    mainViewModel.getAllMatches()
                }
                binding.tvNetworkStatus.text = getString(R.string.text_connectivity)
                binding.llNetworkStatus.apply {
                    setBackgroundColor(getColorRes(R.color.network_connected))

                    animate()
                        .alpha(1.0f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                visibility = View.GONE
                            }
                        })
                }
            }
        }
    }

    private fun getColorRes(@ColorRes id: Int): Int {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            requireContext().getColor(id)
        } else {
            requireContext().resources.getColor(id)
        }
    }
}