package com.annevonwolffen.behancer.ui

import com.annevonwolffen.behancer.ui.common.BaseView
import com.annevonwolffen.domain.Project

interface ProjectsView : BaseView {

    fun showProjects(projects: List<Project>)
}