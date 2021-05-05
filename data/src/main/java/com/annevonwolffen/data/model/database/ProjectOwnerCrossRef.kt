package com.annevonwolffen.data.model.database

import androidx.room.Entity

@Entity(primaryKeys = ["projectId", "ownerId"])
class ProjectOwnerCrossRef(
    val projectId: Int,
    val ownerId: Int
)