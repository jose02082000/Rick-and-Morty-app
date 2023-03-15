package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.model.CharactersResultModel
import com.example.rickandmortyapp.data.repository.CharactersRepository

class GetCharactersUseCase {
    private val repository by lazy { CharactersRepository() }

    suspend operator fun invoke(): List<CharactersResultModel> {
        val response = repository.getCharactersList()
        return if (response == null || response.results.isEmpty()) {
            emptyList<CharactersResultModel>()
        } else {
            response.results
        }
    }
}
