package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.api.BehanceApi
import com.annevonwolffen.data.api.RestDataSource
import retrofit2.Retrofit
import toothpick.config.Module

class NetworkModule : Module() {

    init {
        bind(Retrofit::class.java).toProviderInstance(RetrofitProvider())
        bind(BehanceApi::class.java).toProvider(BehanceApiProvider::class.java).providesSingleton()
        bind(DataSource::class.java).to(RestDataSource::class.java).singleton()
    }
}