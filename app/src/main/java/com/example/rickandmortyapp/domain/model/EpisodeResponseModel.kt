package com.example.rickandmortyapp.domain.model

import com.google.gson.annotations.SerializedName

data class EpisodeResponseModel(
    @SerializedName("info") var info: EpisodesInfoModel? = EpisodesInfoModel(),
    @SerializedName("results") var results: ArrayList<EpisodesResultModel> = arrayListOf(),
)

data class EpisodesInfoModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null,
)

data class EpisodesResultModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String? = null,
    @SerializedName("air_date") var airDate: String? = null,
    @SerializedName("episode") var episode: String? = null,
    @SerializedName("characters") var characters: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null,
)
