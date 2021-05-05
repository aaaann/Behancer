package com.annevonwolffen.data

import com.annevonwolffen.data.model.Project

interface LocalDataSource : DataSource {
    fun insertProjects(projects: List<Project>)
}