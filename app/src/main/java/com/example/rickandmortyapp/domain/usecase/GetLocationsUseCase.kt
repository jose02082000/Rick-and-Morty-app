package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.data.repository.LocationsRepository
import com.example.rickandmortyapp.domain.model.LocationsResultsModel

class GetLocationsUseCase {
    private val repository by lazy { LocationsRepository() }

    suspend operator fun invoke(): List<LocationsResultsModel> {
        val response = repository.getLocationList()
        return if (response == null || response.results.isEmpty()) {
            emptyList()
        } else {
            response.results
        }
    }
}
