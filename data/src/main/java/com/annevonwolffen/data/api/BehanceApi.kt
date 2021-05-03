package com.annevonwolffen.data.api

import com.annevonwolffen.data.model.ProjectsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BehanceApi {

    @GET("v2/projects")
    fun getProjects(): Single<ProjectsResponse>
}