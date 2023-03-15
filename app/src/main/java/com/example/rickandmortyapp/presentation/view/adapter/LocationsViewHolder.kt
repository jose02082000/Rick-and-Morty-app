package com.example.rickandmortyapp.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.databinding.LocationsRecyclerviewBinding

class LocationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = LocationsRecyclerviewBinding.bind(view)
    fun render(locationsResultsModel: LocationsResultsModel, onItemSelected: (Int) -> Unit) {
        binding.tvNameLocation.text = locationsResultsModel.name

        binding.root.setOnClickListener { onItemSelected(locationsResultsModel.id) }
    }
}
