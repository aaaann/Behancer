package com.annevonwolffen.behancer.di

import android.content.Context
import com.annevonwolffen.behancer.ui.ProjectsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, DomainModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(projectsFragment: ProjectsFragment)
}