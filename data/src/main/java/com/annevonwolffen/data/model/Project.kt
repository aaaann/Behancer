package com.annevonwolffen.data.model

import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PROJECT")
class Project(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("published_on")
    val publishedOn: Long,
    @SerializedName("url")
    val url: String,
    @Ignore
    @SerializedName("covers")
    val cover: Cover,
    @Ignore
    @SerializedName("owners")
    val owners: List<User>
)