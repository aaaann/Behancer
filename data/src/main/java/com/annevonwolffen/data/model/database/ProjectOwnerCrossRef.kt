package com.annevonwolffen.data.model.database

import androidx.room.Entity

@Entity(primaryKeys = ["projectId", "userId"])
class ProjectOwnerCrossRef(
    val projectId: Int,
    val userId: Int
)