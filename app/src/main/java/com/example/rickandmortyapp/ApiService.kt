package com.example.rickandmortyapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{endpoint}")
    suspend fun getCharacters(@Path("endpoint") endpoint: String): Response<CharacterResponseModel>
}
