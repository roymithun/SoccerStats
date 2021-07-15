package com.inhouse.soccerstats.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.inhouse.soccerstats.R
import com.inhouse.soccerstats.databinding.FragmentDetailBinding
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.matchDate
import com.inhouse.soccerstats.model.matchTime
import com.inhouse.soccerstats.model.scoreSplit
import com.inhouse.soccerstats.ui.detail.viewmodel.DetailViewModel
import com.inhouse.soccerstats.utils.getInitials
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val detailViewModel: DetailViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchId = DetailFragmentArgs.fromBundle(requireArguments()).matchId
        viewLifecycleOwner.lifecycleScope.launch {
            detailViewModel.getMatch(matchId).collect {
                bindUiElements(it)
            }
        }
    }

    private fun bindUiElements(match: Match) {
        binding.matchDetail.ivTeamA.load(match.linkA) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }
        binding.matchDetail.ivTeamB.load(match.linkB) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.matchDetail.tvTeamAName.text = getInitials(match.teamA)
        binding.matchDetail.tvTeamBName.text = getInitials(match.teamB)

        binding.matchDetail.tvMatchDate.text = match.matchDate()
        binding.matchDetail.tvMatchTime.text = match.matchTime()

        binding.matchDetail.scoreCardA.tvScore.text = match.scoreSplit().first
        binding.matchDetail.scoreCardB.tvScore.text = match.scoreSplit().second
    }
}