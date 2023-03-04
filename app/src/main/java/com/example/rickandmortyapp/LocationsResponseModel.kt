package com.example.rickandmortyapp

import com.google.gson.annotations.SerializedName

data class LocationsResponseModel(
    @SerializedName("info") var info: LocationsInfoModel? = LocationsInfoModel(),
    @SerializedName("results") var results: ArrayList<LocationsResultsModel> = arrayListOf(),
)

data class LocationsInfoModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null,
)

data class LocationsResultsModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("dimension") var dimension: String? = null,
    @SerializedName("residents") var residents: ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null,
)
