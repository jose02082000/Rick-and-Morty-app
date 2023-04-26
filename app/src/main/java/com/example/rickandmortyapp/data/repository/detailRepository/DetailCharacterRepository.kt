package com.example.rickandmortyapp.data.repository.detailRepository

import com.example.rickandmortyapp.data.repository.remote.details.DetailCharacterRemote
import com.example.rickandmortyapp.domain.model.CharactersResultModel

class DetailCharacterRepository {
    private val api = DetailCharacterRemote()

    suspend fun getDetailCharacters(id: String): CharactersResultModel? {
        return api.getDetailCharacters(id)
    }
}
