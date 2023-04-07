package com.example.rickandmortyapp.data.repository.remote

import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.domain.model.LocationsResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailLocationRemote {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDetailLocations(id: String): LocationsResultsModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getLocationsDetail(id)
            response.body()
        }
    }
}
