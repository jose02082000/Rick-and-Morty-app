package com.example.rickandmortyapp.presentation.viewmodel.detail

import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.domain.usecase.details.GetDetailEpisodeUseCase

class DetailEpisodeViewModel {
    private val useCase by lazy { GetDetailEpisodeUseCase() }

    suspend fun getDetailEpisodes(id: String): EpisodesResultModel {
        return useCase.invoke(id)
    }
}
