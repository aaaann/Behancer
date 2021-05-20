package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.api.BehanceApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class BehanceApiProvider : Provider<BehanceApi> {

    @Inject
    lateinit var retrofit: Retrofit

    override fun get(): BehanceApi {
        return retrofit.create(BehanceApi::class.java)
    }
}