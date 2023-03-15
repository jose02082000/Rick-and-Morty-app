package com.example.rickandmortyapp.data.repository.remote

import com.example.rickandmortyapp.domain.model.CharacterResponseModel
import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRemote {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharactersList(): CharacterResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getCharacters()
            response.body()
        }
    }
}
