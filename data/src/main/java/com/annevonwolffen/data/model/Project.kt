package com.annevonwolffen.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PROJECT")
data class Project @JvmOverloads constructor(
    @PrimaryKey
    @SerializedName("id")
    val projectId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("published_on")
    val publishedOn: Long,
    @SerializedName("url")
    val url: String,
    @Ignore
    @SerializedName("covers")
    val cover: Cover = Cover(""),
    @Ignore
    @SerializedName("owners")
    val owners: List<User> = emptyList()
)