package com.inhouse.soccerstats.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inhouse.soccerstats.databinding.MatchSummaryItemBinding
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.matchDate
import com.inhouse.soccerstats.model.matchTime
import com.inhouse.soccerstats.model.scoreSplit
import com.inhouse.soccerstats.utils.getInitials

class MatchAdapter : ListAdapter<Match, MatchAdapter.MatchViewHolder>(MatchItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchSummaryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MatchViewHolder(private val binding: MatchSummaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {

            binding.ivTeamA.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU") {
            }
            binding.ivTeamB.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU") {
            }

            binding.tvTeamAName.text = getInitials(match.teamA)
            binding.tvTeamBName.text = getInitials(match.teamB)

            binding.tvMatchDate.text = match.matchDate()
            binding.tvMatchTime.text = match.matchTime()

            binding.scoreCardA.tvScore.text = match.scoreSplit().first
            binding.scoreCardB.tvScore.text = match.scoreSplit().second
        }
    }

    class MatchItemDiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean =
            oldItem.teamA == newItem.teamA &&
                    oldItem.teamB == newItem.teamB &&
                    oldItem.date == newItem.date &&
                    oldItem.score == newItem.score

    }
}