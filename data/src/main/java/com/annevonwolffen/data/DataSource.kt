package com.annevonwolffen.data

import com.annevonwolffen.data.model.Project
import io.reactivex.Single

interface DataSource {
    fun getProjects(): Single<List<Project>>
}