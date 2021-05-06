package com.annevonwolffen.behancer

import android.app.Application
import com.annevonwolffen.behancer.di.AppComponent
import com.annevonwolffen.behancer.di.DaggerAppComponent

class AppDelegate : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}