package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.data.repository.EpisodesRepository
import com.example.rickandmortyapp.domain.model.EpisodesResultModel

class GetEpisodesUSeCase {
    /**
     * al interior de esta clase vamos a realizar lo sgte
     * 1) crear y conectar con el repositorio que necesitamos que es donde estan alojados los datos que nosotros necesitamos
     * en la capa de data.repository creamos una clase con el nombre de EpisodesRepository() lo hacemos de la sgte manera private val repository by lazy { EpisodesRepository() }
     *
     * con los otros pasos ya logrados exitosamente y haber vuelto desde el repository haremos lo sgte vamos a crear una suspend operator fun invoke
     * donde vamos a retornar una lista de EpisodeResulModel al interior de la funcion crearemos una val response que es igual al
     * repository.getEpisodesList() y aca es donde se hace la validacion para conocer si el cuerpo de la peticion si contiene los datos que necesitamos
     * o si no esto lo controlamos con logica
     */

    private val repository by lazy { EpisodesRepository() }

    suspend operator fun invoke(): List<EpisodesResultModel> {
        val response = repository.getEpisodesList()
        return if (response == null || response.results.isEmpty()) {
            emptyList()
        } else {
            response.results
        }
    }
}
