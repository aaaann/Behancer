package com.annevonwolffen.behancer.di

import com.annevonwolffen.behancer.utils.SchedulersProvider
import com.annevonwolffen.behancer.utils.SchedulersProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideSchedulers(schedulersProviderImpl: SchedulersProviderImpl): SchedulersProvider
}