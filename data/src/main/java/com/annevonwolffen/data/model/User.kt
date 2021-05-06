package com.annevonwolffen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "USER")
class User constructor(
    @PrimaryKey
    @SerializedName("id")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("location")
    val location: String
)