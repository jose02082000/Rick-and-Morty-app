package com.example.rickandmortyapp.data.repository.detailRepository

import com.example.rickandmortyapp.data.repository.remote.details.DetailEpisodeRemote
import com.example.rickandmortyapp.domain.model.EpisodesResultModel

class DetailEpisodeRepository {
    private val api = DetailEpisodeRemote()

    suspend fun getDetailEpisode(id: String): EpisodesResultModel? {
        return api.getDetailEpisodes(id)
    }
}
