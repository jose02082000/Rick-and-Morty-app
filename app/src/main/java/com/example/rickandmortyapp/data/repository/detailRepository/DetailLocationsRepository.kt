package com.example.rickandmortyapp.data.repository.detailRepository

import com.example.rickandmortyapp.data.repository.remote.details.DetailLocationRemote
import com.example.rickandmortyapp.domain.model.LocationsResultsModel

class DetailLocationsRepository {
    private val api = DetailLocationRemote()

    suspend fun getDetailLocations(id: String): LocationsResultsModel? {
        return api.getDetailLocations(id)
    }
}
