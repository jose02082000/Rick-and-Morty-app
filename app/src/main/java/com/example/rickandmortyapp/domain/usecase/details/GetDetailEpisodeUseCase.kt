package com.example.rickandmortyapp.domain.usecase.details

import com.example.rickandmortyapp.data.repository.detailRepository.DetailEpisodeRepository
import com.example.rickandmortyapp.domain.model.EpisodesResultModel

class GetDetailEpisodeUseCase {
    private val repository by lazy { DetailEpisodeRepository() }

    suspend operator fun invoke(id: String): EpisodesResultModel {
        return repository.getDetailEpisode(id) ?: EpisodesResultModel(id)
    }
}
