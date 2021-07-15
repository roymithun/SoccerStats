package com.inhouse.soccerstats.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inhouse.soccerstats.R
import com.inhouse.soccerstats.model.Match

class MatchAdapter : ListAdapter<Match, MatchAdapter.MatchViewHolder>(MatchItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.match_summary_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val ivTeamA = holder.itemView.findViewById<ImageView>(R.id.iv_team_a)
        val ivTeamB = holder.itemView.findViewById<ImageView>(R.id.iv_team_b)
        ivTeamA.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU") {
        }
        ivTeamB.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU") {
        }
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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