package com.annevonwolffen.behancer.ui

import com.annevonwolffen.behancer.ui.common.BasePresenter
import moxy.InjectViewState

@InjectViewState
class ProjectsPresenter : BasePresenter<ProjectsView>() {

    fun getProjects() {
        viewState.showProjects()
    }
}