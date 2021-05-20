package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.BuildConfig
import com.annevonwolffen.data.api.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider

class RetrofitProvider : Provider<Retrofit> {
    override fun get(): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            // need for interceptors
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}