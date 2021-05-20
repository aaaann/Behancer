package com.annevonwolffen.data.api

import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.model.Project
import io.reactivex.Single
import toothpick.InjectConstructor

@InjectConstructor
class RestDataSource (private val api: BehanceApi) : DataSource {

    override fun getProjects(): Single<List<Project>> = api.getProjects().map { it.projects }
}