package com.annevonwolffen.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Query

import com.annevonwolffen.data.model.Cover
import com.annevonwolffen.data.model.Project
import com.annevonwolffen.data.model.User
import com.annevonwolffen.data.model.database.ProjectOwnerCrossRef
import com.annevonwolffen.data.model.database.ProjectWithCoverAndOwners

@Dao
interface BehanceDao {

    @Transaction
    @Query("SELECT * FROM PROJECT")
    fun selectAllProjects(): List<ProjectWithCoverAndOwners>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjects(projects: List<Project>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovers(covers: List<Cover>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoin(join: List<ProjectOwnerCrossRef>)
}