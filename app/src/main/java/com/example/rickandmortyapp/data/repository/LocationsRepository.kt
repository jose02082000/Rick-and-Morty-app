package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.repository.remote.LocationRemote
import com.example.rickandmortyapp.domain.model.LocationsResponseModel

class LocationsRepository {
    private val api = LocationRemote()

    suspend fun getLocationList(): LocationsResponseModel? {
        return api.getLocationList()
    }
}
