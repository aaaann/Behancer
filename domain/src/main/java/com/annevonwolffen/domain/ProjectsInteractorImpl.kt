package com.annevonwolffen.domain

import io.reactivex.Single

class ProjectsInteractorImpl(private val projectsRepository: ProjectsRepository) :
    ProjectsInteractor {
    override fun getProjects(): Single<List<Project>> {
        return projectsRepository.getProjects()
    }
}