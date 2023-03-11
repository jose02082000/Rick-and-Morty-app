package com.example.rickandmortyapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.databinding.CharactersRecyclerviewBinding
import com.squareup.picasso.Picasso

class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = CharactersRecyclerviewBinding.bind(view)

    fun render(charactersResultModel: CharactersResultModel, onItemSelected: (String) -> Unit) {
        binding.tvNameCharacters.text = charactersResultModel.name
        binding.tvIdCharacters.text = charactersResultModel.id.toString()
        Picasso.get().load(charactersResultModel.image).into(binding.ivCharacters)

        binding.root.setOnClickListener { onItemSelected(charactersResultModel.name) }
    }
}
