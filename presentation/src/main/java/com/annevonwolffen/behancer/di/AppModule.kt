package com.annevonwolffen.behancer.di

import com.annevonwolffen.behancer.utils.SchedulersProvider
import com.annevonwolffen.behancer.utils.SchedulersProviderImpl
import toothpick.config.Module

class AppModule : Module() {
    init {
        bind(SchedulersProvider::class.java).toInstance(SchedulersProviderImpl())
    }
}