package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.repository.remote.CharactersRemote
import com.example.rickandmortyapp.domain.model.CharacterResponseModel

class CharactersRepository {
    private val api = CharactersRemote()

    suspend fun getCharactersList(): CharacterResponseModel? {
        return api.getCharactersList()
    }
}
