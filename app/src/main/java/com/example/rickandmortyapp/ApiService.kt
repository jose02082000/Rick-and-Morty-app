package com.example.rickandmortyapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{character}")
    suspend fun getCharacters(@Path("character") characters: String): Response<CharacterResponse>
}
