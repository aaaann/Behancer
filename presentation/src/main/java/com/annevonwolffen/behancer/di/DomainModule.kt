package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.ProjectsRepositoryImpl
import com.annevonwolffen.data.mapper.ProjectMapper
import com.annevonwolffen.domain.ProjectsInteractor
import com.annevonwolffen.domain.ProjectsInteractorImpl
import com.annevonwolffen.domain.ProjectsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideProjectsInteractor(
        dataSource: DataSource,
        localDataSource: LocalDataSource
    ): ProjectsInteractor {
        val repository: ProjectsRepository =
            ProjectsRepositoryImpl(dataSource, localDataSource, ProjectMapper())
        return ProjectsInteractorImpl(repository)
    }
}