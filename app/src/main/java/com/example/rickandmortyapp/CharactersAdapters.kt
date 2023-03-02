package com.example.rickandmortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CharactersAdapters(
    var charactersList: List<CharactersResultModel> = emptyList(),
) :
    RecyclerView.Adapter<CharactersViewHolder>() {

    fun updateList(list: List<CharactersResultModel>) {
        charactersList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersViewHolder(
            layoutInflater.inflate(
                R.layout.characters_recyclerview,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.render(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}
