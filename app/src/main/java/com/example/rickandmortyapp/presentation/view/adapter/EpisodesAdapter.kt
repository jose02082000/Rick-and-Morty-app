package com.example.rickandmortyapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.R

class EpisodesAdapter(
    var episodesList: List<EpisodesResultModel> = emptyList(),
    private val onItemSelected: (String) -> Unit,
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
        holder.render(episodesList[position], onItemSelected)
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }
}
