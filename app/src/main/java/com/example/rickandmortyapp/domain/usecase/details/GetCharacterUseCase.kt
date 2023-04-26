package com.example.rickandmortyapp.domain.usecase.details

import com.example.rickandmortyapp.data.repository.detailRepository.DetailCharacterRepository
import com.example.rickandmortyapp.domain.model.CharactersResultModel

class GetCharacterUseCase {
    private val repository by lazy { DetailCharacterRepository() }

    suspend operator fun invoke(id: String): CharactersResultModel {
        return repository.getDetailCharacters(id) ?: CharactersResultModel(id)
    }
}
