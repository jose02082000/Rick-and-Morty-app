package com.example.rickandmortyapp.presentation.viewmodel.detail

import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import com.example.rickandmortyapp.domain.usecase.details.GetDetailLocationsUseCase

class DetailLocationsViewModel {
    private val useCase by lazy { GetDetailLocationsUseCase() }

    suspend fun getDetailLocations(): LocationsResultsModel {
        return useCase.invoke()
    }
}
