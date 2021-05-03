package com.annevonwolffen.data.model

import com.google.gson.annotations.SerializedName

class Project (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("published_on") val publishedOn: Long,
    @SerializedName("url") val url: String,
    @SerializedName("covers") val covers: List<Cover>,
    @SerializedName("owners") val owners: List<User>
)