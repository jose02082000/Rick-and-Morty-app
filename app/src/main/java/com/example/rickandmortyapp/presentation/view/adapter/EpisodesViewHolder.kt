package com.example.rickandmortyapp.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.databinding.EpisodesRecyclerviewBinding

class EpisodesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = EpisodesRecyclerviewBinding.bind(view)
    fun render(episodesResultModel: EpisodesResultModel, onItemSelected: (Int) -> Unit) {
        binding.name.text = episodesResultModel.name
        binding.episode.text = episodesResultModel.episode

        binding.root.setOnClickListener { onItemSelected(episodesResultModel.id) }
    }
}
