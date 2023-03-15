package com.example.rickandmortyapp.data.repository.network

import com.example.rickandmortyapp.domain.model.CharacterResponseModel
import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.domain.model.EpisodeResponseModel
import com.example.rickandmortyapp.domain.model.LocationsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponseModel>

    @GET("character/{id}")
    suspend fun getCharactersDetail(@Path("id") id: String): Response<CharactersResultModel>

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodeResponseModel>

    @GET("location")
    suspend fun getLocations(): Response<LocationsResponseModel>
}
