package com.annevonwolffen.domain

import io.reactivex.Single

interface ProjectsRepository {
    fun getProjects(): Single<List<Project>>
}