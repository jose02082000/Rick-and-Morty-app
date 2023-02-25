package com.example.rickandmortyapp

import com.google.gson.annotations.SerializedName

data class CharacterResponseModel(
    @SerializedName("info") var info: CharactersInfoModel? = CharactersInfoModel(),
    @SerializedName("results") var results: ArrayList<CharactersResultModel> = arrayListOf()
)

data class CharactersInfoModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null
)

data class CharactersResultModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("originModel") var origin: OriginModel? = OriginModel(),
    @SerializedName("location") var location: LocationModel? = LocationModel(),
    @SerializedName("image") var image: String? = null,
    @SerializedName("episode") var episode: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null

)

data class OriginModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

data class LocationModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
