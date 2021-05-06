package com.annevonwolffen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "COVER")
data class Cover (
    @PrimaryKey
    @SerializedName("original")
    val url: String,

    val projectId: Int = 0
)