package com.annevonwolffen.data

import android.util.Log
import com.annevonwolffen.data.mapper.DataToDomainMapper
import com.annevonwolffen.domain.Project
import com.annevonwolffen.domain.ProjectsRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ProjectsRepositoryImpl(
    private val dataSource: DataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: DataToDomainMapper<com.annevonwolffen.data.model.Project, Project>
) : ProjectsRepository {
    override fun getProjects(): Single<List<Project>> {
        return dataSource.getProjects()
            .timeout(TIMEOUT, TimeUnit.SECONDS)
            .doOnSuccess { localDataSource.insertProjects(it) }
            .doOnError { Log.d(TAG, it.message.orEmpty()) }
            .onErrorResumeNext { localDataSource.getProjects() }
            .map { it.map { project -> mapper.map(project) } }
    }

    private companion object {
        const val TIMEOUT = 20L
        const val TAG = "ProjectsRepository"
    }
}