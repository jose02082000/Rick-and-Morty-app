package com.example.rickandmortyapp.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.databinding.ItemLoadingBinding

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemLoadingBinding.bind(view)

    fun render() {
        /*
        binding.progressBar.visibility = if (isLoading) {
            VISIBLE
        } else {
            GONE
        }
        */
    }
}
