package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.repository.remote.EpisodesRemote
import com.example.rickandmortyapp.domain.model.EpisodeResponseModel

class EpisodesRepository {
    /**
     * al interior de esta clase vamos a hacer dos cosas tambien la primera sería crear un valor privado llamado api que va a ser igual a una clase
     * que vamos a crear que se llama en este caso EpisodesRemote, esta clase nosotros la vamos a crear en el sgte directorio al interior de data
     * repository debemos de crear un paquete con nombre remote y al interior de este creamos la clase EpisodesRemote()
     *
     * Ya creado el primer paso ahora debemos de conectar la capa remote con esta que es el repository la forma de hacerlo es la siguiente
     * se crea una funcion suspendida con el mismo nombre que tenemos en el remote esta debe retornar un EpisodesResultModel en este caso esto se retorna
     * de la sgte manera api.getEpisodesList() y listo
     *
     * ahora debemos de conectar esa funcion en el useCase nos vamos al useCase
     *
     */

    private val api = EpisodesRemote()

    suspend fun getEpisodesList(): EpisodeResponseModel? {
        return api.getEpisodesList()
    }
}
