package com.example.rickandmortyapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.databinding.EpisodesRecyclerviewBinding

class EpisodesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = EpisodesRecyclerviewBinding.bind(view)
    fun render(episodesResultModel: EpisodesResultModel) {
        binding.name.text = episodesResultModel.name
        binding.episode.text = episodesResultModel.episode
    }
}
