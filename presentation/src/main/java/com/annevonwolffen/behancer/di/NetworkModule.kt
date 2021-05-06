package com.annevonwolffen.behancer.di

import com.annevonwolffen.data.BuildConfig
import com.annevonwolffen.data.DataSource
import com.annevonwolffen.data.api.ApiKeyInterceptor
import com.annevonwolffen.data.api.BehanceApi
import com.annevonwolffen.data.api.RestDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(ApiKeyInterceptor())
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            // need for interceptors
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBehanceApi(retrofit: Retrofit): BehanceApi {
        return retrofit.create(BehanceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRestDataSource(behanceApi: BehanceApi): DataSource {
        return RestDataSource(behanceApi)
    }
}