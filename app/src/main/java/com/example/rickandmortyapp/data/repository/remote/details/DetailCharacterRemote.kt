package com.example.rickandmortyapp.data.repository.remote.details

import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.domain.model.CharactersResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailCharacterRemote {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDetailCharacters(id: String): CharactersResultModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getCharactersDetail(id)
            response.body()
        }
    }
}
