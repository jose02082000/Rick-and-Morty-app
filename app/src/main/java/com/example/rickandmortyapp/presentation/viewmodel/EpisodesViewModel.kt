package com.example.rickandmortyapp.presentation.viewmodel

import com.example.rickandmortyapp.domain.model.EpisodesResultModel
import com.example.rickandmortyapp.domain.usecase.GetEpisodesUSeCase

class EpisodesViewModel {
    /**
     * al interior de esta clase se crea el useCase y tambien se crea una suspend function con el nombre de la peticion que vamos a realizar ya que
     * vamos a conectar desde este directorio con el de las peticiones que es network
     * 1) se crea el use case y se inicializa en by lazy al interior de las llaves del lazy colocamos con nombre de la peticion que vamos a realizar
     * en el directorio de domain se crea un paquete llamado useCase en donde los vamos a alojar a todos al interior de este
     *
     * luego de haber vuelto desde el useCase y haber recorrido las otras capas volvemos hasta el viewModel en donde vamos a terminar de enlazar todo
     * creamos una suspend fun con el mismo nombre que venimos trabajando  esto debe de retornar una lista de resultModel y en el cuerpo de la funcion
     * retornamos el usecase.invoke
     *
     * terminado esto debemos de entrelazar la segunda funcion que se hace desde la activity
     */
    private val useCase by lazy { GetEpisodesUSeCase() }

    suspend fun getEpisodesList(): List<EpisodesResultModel> {
        return useCase.invoke()
    }
}
