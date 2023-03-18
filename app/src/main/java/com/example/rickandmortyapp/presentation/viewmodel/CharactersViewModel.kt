package com.example.rickandmortyapp.presentation.viewmodel

import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.domain.usecase.GetCharactersUseCase

class CharactersViewModel {
    private val useCase by lazy { GetCharactersUseCase() }

    suspend fun getCharactersList(): MutableList<CharactersResultModel?> {
        return useCase.invoke().toMutableList()
    }
}
