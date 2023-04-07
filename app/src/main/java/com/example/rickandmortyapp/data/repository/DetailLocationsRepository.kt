package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.repository.remote.DetailLocationRemote
import com.example.rickandmortyapp.domain.model.LocationsResultsModel

class DetailLocationsRepository {
    private val api = DetailLocationRemote()

    suspend fun getDetailLocations(id: String): LocationsResultsModel? {
        return api.getDetailLocations(id)
    }
}
