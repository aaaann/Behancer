package com.annevonwolffen.data.database

import com.annevonwolffen.data.LocalDataSource
import com.annevonwolffen.data.model.Project
import com.annevonwolffen.data.model.database.ProjectOwnerCrossRef
import io.reactivex.Single

class DbDataSource(private val dao: BehanceDao) : LocalDataSource {

    override fun getProjects(): Single<List<Project>> {
        return Single.fromCallable {
            dao.selectAllProjects()
                .map {
                    Project(
                        it.project.id,
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
            insertCovers(projects.map { it.cover })
            insertUsers(projects.flatMap { it.owners })
            insertJoin(projects.flatMap { project ->
                project.owners.map { user -> ProjectOwnerCrossRef(project.id, user.id) }
            })
        }
    }
}