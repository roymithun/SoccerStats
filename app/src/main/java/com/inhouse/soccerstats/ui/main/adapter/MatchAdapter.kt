package com.inhouse.soccerstats.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inhouse.soccerstats.R
import com.inhouse.soccerstats.databinding.MatchSummaryItemBinding
import com.inhouse.soccerstats.model.Match
import com.inhouse.soccerstats.model.matchDate
import com.inhouse.soccerstats.model.matchTime
import com.inhouse.soccerstats.model.scoreSplit
import com.inhouse.soccerstats.utils.getInitials

class MatchAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Match, MatchAdapter.MatchViewHolder>(MatchItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchSummaryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class MatchViewHolder(private val binding: MatchSummaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match, clickListener: OnClickListener) {
            binding.root.setOnClickListener { clickListener.onClick(match) }
            binding.ivTeamA.load(match.linkA) {
                placeholder(R.drawable.ic_photo)
                error(R.drawable.ic_broken_image)
            }
            binding.ivTeamB.load(match.linkB) {
                placeholder(R.drawable.ic_photo)
                error(R.drawable.ic_broken_image)
            }

            binding.tvTeamAName.text = getInitials(match.teamA)
            binding.tvTeamBName.text = getInitials(match.teamB)

            binding.tvMatchDate.text = match.matchDate()
            binding.tvMatchTime.text = match.matchTime()

            binding.scoreCardA.tvScore.text = match.scoreSplit().first
            binding.scoreCardB.tvScore.text = match.scoreSplit().second
        }
    }

    interface OnClickListener {
        fun onClick(match: Match)
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