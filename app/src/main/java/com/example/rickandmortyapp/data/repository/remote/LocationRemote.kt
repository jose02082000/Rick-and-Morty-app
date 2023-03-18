package com.example.rickandmortyapp.data.repository.remote

import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.domain.model.LocationsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class LocationRemote {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getLocationList(): LocationsResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getLocations()
            response.body()
        }
    }
}
