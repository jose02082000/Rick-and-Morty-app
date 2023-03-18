package com.example.rickandmortyapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.model.CharactersResultModel

class CharactersAdapters(
    private var charactersList: List<CharactersResultModel?>,
    private val onItemSelected: (String) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    fun updateListCharacters(list: List<CharactersResultModel>) {
        charactersList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view: View =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.characters_recyclerview,
                    parent,
                    false
                )
            CharactersViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_loading,
                    parent,
                    false
                )
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is CharactersViewHolder) {
            charactersList[position]?.let { charactersList ->
                viewHolder.render(charactersList, onItemSelected)
            }
        } else if (viewHolder is LoadingViewHolder) {
            viewHolder.render()
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    /**
     * El siguiente método decide el tipo de ViewHolder a mostrar en el RecyclerView
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return if (charactersList[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }
}
