package com.example.rickandmortyapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.R

class LocationAdapter(
    var locationsList: List<LocationsResultsModel>,
    private val onItemSelected: (Int) -> Unit,
) :
    RecyclerView.Adapter<LocationsViewHolder>() {

    fun updateList(list: List<LocationsResultsModel>) {
        locationsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LocationsViewHolder(
            layoutInflater.inflate(
                R.layout.locations_recyclerview,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.render(locationsList[position], onItemSelected)
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }
}
