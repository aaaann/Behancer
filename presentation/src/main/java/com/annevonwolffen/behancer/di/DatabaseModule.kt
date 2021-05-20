package com.annevonwolffen.behancer.di

import android.content.Context
import androidx.room.Room
import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.database.BehanceDatabase
import com.annevonwolffen.data.database.BehanceDatabase.Companion.DB_NAME
import com.annevonwolffen.data.database.DbDataSource
import toothpick.config.Module

class DatabaseModule(applicationContext: Context) : Module() {

    init {
        val database = Room.databaseBuilder(
            applicationContext,
            BehanceDatabase::class.java,
            DB_NAME
        ).build()
        val behanceDao = database.behanceDao()
        bind(LocalDataSource::class.java).toInstance(DbDataSource(behanceDao))
    }
}