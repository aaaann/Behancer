package com.annevonwolffen.data.model.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.annevonwolffen.data.model.Cover
import com.annevonwolffen.data.model.Project
import com.annevonwolffen.data.model.User

class ProjectWithCoverAndOwners(
    @Embedded
    val project: Project,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "projectId"
    )
    val cover: Cover,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "userId",
        associateBy = Junction(ProjectOwnerCrossRef::class)
    )
    val owners: List<User>
)