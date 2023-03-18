package com.example.rickandmortyapp.presentation.viewmodel

import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.domain.usecase.GetLocationsUseCase

class LocationsViewModel {
    private val useCase by lazy { GetLocationsUseCase() }

    suspend fun getLocationList(): List<LocationsResultsModel> {
        return useCase.invoke()
    }
}
