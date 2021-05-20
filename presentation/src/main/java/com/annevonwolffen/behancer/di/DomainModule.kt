package com.annevonwolffen.behancer.di

import com.annevonwolffen.domain.ProjectsInteractor
import toothpick.config.Module


class DomainModule : Module() {

    init {
        bind(ProjectsInteractor::class.java).toProvider(ProjectsInteractorProvider::class.java).providesSingleton()
    }
}