package com.example.rickandmortyapp.domain.usecase.details

import com.example.rickandmortyapp.data.repository.DetailLocationsRepository
import com.example.rickandmortyapp.domain.model.LocationsResponseModel

class GetDetailLocationsUseCase {
    private val repository by lazy { DetailLocationsRepository() }

    suspend operator fun invoke(): LocationsResponseModel {
        val response = repository.getDetailLocations()
        return if (response == null || response.results.isEmpty()) {
        } else {
            response.results
        }
    }
}
