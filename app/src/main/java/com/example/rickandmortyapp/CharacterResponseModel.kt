package com.example.rickandmortyapp

import com.google.gson.annotations.SerializedName

data class CharacterResponseModel(
    @SerializedName("info") var info: CharactersInfoModel? = CharactersInfoModel(),
    @SerializedName("results") var results: ArrayList<CharactersResultModel> = arrayListOf(),
)

data class CharactersInfoModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null,
)

data class CharactersResultModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: String,
    @SerializedName("species") var species: String,
    @SerializedName("type") var type: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("originModel") var origin: OriginModel,
    @SerializedName("location") var location: LocationModel,
    @SerializedName("image") var image: String,
    @SerializedName("episode") var episode: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String,
    @SerializedName("created") var created: String,

)

data class OriginModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null,
)

data class LocationModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null,
)
