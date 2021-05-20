package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.ProjectsRepositoryImpl
import com.annevonwolffen.data.mapper.ProjectMapper
import com.annevonwolffen.domain.ProjectsInteractor
import com.annevonwolffen.domain.ProjectsInteractorImpl
import javax.inject.Inject
import javax.inject.Provider

class ProjectsInteractorProvider : Provider<ProjectsInteractor> {

    @Inject
    lateinit var dataSource: DataSource

    @Inject
    lateinit var localDataSource: LocalDataSource

    override fun get(): ProjectsInteractor {

        val repository = ProjectsRepositoryImpl(dataSource, localDataSource, ProjectMapper())
        return ProjectsInteractorImpl(repository)
    }
}