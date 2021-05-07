package com.annevonwolffen.data.api

import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.model.Project
import io.reactivex.Single
import javax.inject.Inject

class RestDataSource @Inject constructor(private val api: BehanceApi) : DataSource {

    override fun getProjects(): Single<List<Project>> = api.getProjects().map { it.projects }
}