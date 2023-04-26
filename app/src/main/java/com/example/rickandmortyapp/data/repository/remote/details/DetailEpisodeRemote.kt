package com.example.rickandmortyapp.data.repository.remote.details

import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailEpisodeRemote {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDetailEpisodes(id: String): EpisodesResultModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getEpisodesDetail(id)
            response.body()
        }
    }
}
