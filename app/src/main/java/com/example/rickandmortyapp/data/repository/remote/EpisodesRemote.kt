package com.example.rickandmortyapp.data.repository.remote

import com.example.rickandmortyapp.core.RetrofitHelper
import com.example.rickandmortyapp.data.repository.network.ApiService
import com.example.rickandmortyapp.domain.model.EpisodeResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodesRemote {
    /**
     * en este archivo crearemos un valor privado con el nombre de retrofit y este va a ser igual a RetrofitHelper.getRetrofit()
     * y tambien vamos a crear una suspend fun para por medio de las corrutinas hacer el llamado que debe de ser en esta capa donde vamos a crear una
     * suspend fun con el nombre de la peticion que vamos a realizar se realiza de la manera en que se muestra abajo obviamente con los datos
     * correspondientes y esta funcion debemos de llamarla desde el repository
     */

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getEpisodesList(): EpisodeResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getEpisodes()
            response.body()
        }
    }
}
