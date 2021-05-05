package com.annevonwolffen.domain

import io.reactivex.Single

interface ProjectsInteractor {
    fun getProjects(): Single<List<Project>>
}