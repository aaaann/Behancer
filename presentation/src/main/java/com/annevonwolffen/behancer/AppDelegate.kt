package com.annevonwolffen.behancer

import android.app.Application
import com.annevonwolffen.behancer.di.AppModule
import com.annevonwolffen.behancer.di.DatabaseModule
import com.annevonwolffen.behancer.di.DomainModule
import com.annevonwolffen.behancer.di.NetworkModule
import toothpick.ktp.KTP

class AppDelegate : Application() {
    override fun onCreate() {
        super.onCreate()
        KTP.openScope(APP_SCOPE)
            .installModules(
                AppModule(),
                DatabaseModule(applicationContext),
                NetworkModule(),
                DomainModule()
            )
    }

    companion object {
        const val APP_SCOPE = "APP"
    }
}