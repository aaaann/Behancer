package com.annevonwolffen.data.database

import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.model.Project
import com.annevonwolffen.data.model.database.ProjectOwnerCrossRef
import io.reactivex.Single
import javax.inject.Inject

class DbDataSource @Inject constructor(private val dao: BehanceDao) : LocalDataSource {

    override fun getProjects(): Single<List<Project>> {
        return Single.fromCallable {
            dao.selectAllProjects()
                .map {
                    Project(
                        it.project.projectId,
                        it.project.name,
                        it.project.publishedOn,
                        it.project.url,
                        it.cover,
                        it.owners
                    )
                }
        }
    }

    override fun insertProjects(projects: List<Project>) {
        with(dao) {
            insertProjects(projects)
            insertCovers(projects.map { it.cover.copy(projectId = it.projectId) })
            insertUsers(projects.flatMap { it.owners })
            insertJoin(projects.flatMap { project ->
                project.owners.map { user -> ProjectOwnerCrossRef(project.projectId, user.userId) }
            })
        }
    }
}