package com.example.rickandmortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EpisodesAdapter(
    var episodesList: List<EpisodesResultModel> = emptyList(),
) :
    RecyclerView.Adapter<EpisodesViewHolder>() {

    fun updateListEpisodes(list: List<EpisodesResultModel>) {
        episodesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EpisodesViewHolder(
            layoutInflater.inflate(
                R.layout.episodes_recyclerview,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.render(episodesList[position])
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }
}
