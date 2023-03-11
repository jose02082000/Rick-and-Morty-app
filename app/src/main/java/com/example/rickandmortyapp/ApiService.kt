package com.example.rickandmortyapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{endpoint}")
    suspend fun getCharacters(@Path("endpoint") endpoint: String): Response<CharacterResponseModel>

    @GET("{endpoint}")
    suspend fun getCharactersDetail(@Path("endpoint") name: String): Response<CharactersResultModel>

    @GET("{endpoint}")
    suspend fun getEpisodes(@Path("endpoint") endpoint: String): Response<EpisodeResponseModel>

    @GET("{endpoint}")
    suspend fun getLocations(@Path("endpoint") endpoint: String): Response<LocationsResponseModel>
}
