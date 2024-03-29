package com.annevonwolffen.behancer.di

import android.content.Context
import androidx.room.Room
import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.database.BehanceDao
import com.annevonwolffen.data.database.BehanceDatabase
import com.annevonwolffen.data.database.BehanceDatabase.Companion.DB_NAME
import com.annevonwolffen.data.database.DbDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Singleton
    @Binds
    abstract fun provideDbDataSource(dbDataSource: DbDataSource): LocalDataSource

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(context: Context): BehanceDatabase {
            return Room.databaseBuilder(
                context,
                BehanceDatabase::class.java,
                DB_NAME
            ).build()
        }

        @Singleton
        @Provides
        fun provideBehanceDao(database: BehanceDatabase): BehanceDao {
            return database.behanceDao()
        }
    }
}