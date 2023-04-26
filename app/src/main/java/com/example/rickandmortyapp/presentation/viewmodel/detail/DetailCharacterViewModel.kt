package com.example.rickandmortyapp.presentation.viewmodel.detail

import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.domain.usecase.details.GetCharacterUseCase

class DetailCharacterViewModel {
    private val useCase by lazy { GetCharacterUseCase() }

    suspend fun getDetailCharacters(id: String): CharactersResultModel {
        return useCase.invoke(id)
    }
}
