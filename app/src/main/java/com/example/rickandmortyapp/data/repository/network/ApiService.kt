package com.example.rickandmortyapp.data.repository.network

import com.example.rickandmortyapp.domain.model.* // ktlint-disable no-wildcard-imports
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

    @GET("episode/{id}")
    suspend fun getEpisodesDetail(@Path("id") id: String): Response<EpisodesResultModel>

    @GET("location")
    suspend fun getLocations(): Response<LocationsResponseModel>

    @GET("location/{id}")
    suspend fun getLocationsDetail(@Path("id") id: String): Response<LocationsResultsModel>
}
