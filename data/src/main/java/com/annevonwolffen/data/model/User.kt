package com.annevonwolffen.data.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")
    val id: Int,
    @Transient
    val projectId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("location")
    val location: String
)