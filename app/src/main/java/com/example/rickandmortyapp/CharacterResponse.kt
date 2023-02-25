package com.example.rickandmortyapp

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results") val characters: List<CharactersItemResponse>,
)

data class CharactersItemResponse(
    @SerializedName("id") val idCharacters: String,
    @SerializedName("name") val nameCharacters: String,
    @SerializedName("image") val imageCharacters: String,
)
