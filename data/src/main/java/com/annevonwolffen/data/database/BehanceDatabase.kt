package com.annevonwolffen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.annevonwolffen.data.model.Cover
import com.annevonwolffen.data.model.Project
import com.annevonwolffen.data.model.User
import com.annevonwolffen.data.model.database.ProjectOwnerCrossRef

@Database(
    entities = [Project::class, Cover::class, User::class, ProjectOwnerCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class BehanceDatabase : RoomDatabase() {
    abstract fun behanceDao(): BehanceDao

    companion object {
        const val DB_NAME = "Behance.db"
    }
}