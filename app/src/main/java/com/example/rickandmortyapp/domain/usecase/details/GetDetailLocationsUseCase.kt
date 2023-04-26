package com.example.rickandmortyapp.domain.usecase.details

import com.example.rickandmortyapp.data.repository.detailRepository.DetailLocationsRepository
import com.example.rickandmortyapp.domain.model.LocationsResultsModel

class GetDetailLocationsUseCase {
    private val repository by lazy { DetailLocationsRepository() }

    suspend operator fun invoke(id: String): LocationsResultsModel {
        val response: LocationsResultsModel? = repository.getDetailLocations(id)
        return if (response == null || !response.error.isNullOrEmpty()) {
            LocationsResultsModel()
        } else {
            response
        }
    }
}
