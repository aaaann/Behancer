package com.annevonwolffen.data.model

import com.google.gson.annotations.SerializedName

class ProjectsResponse(@SerializedName("projects") val projects: List<Project>)