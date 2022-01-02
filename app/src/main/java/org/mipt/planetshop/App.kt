package org.mipt.planetshop

import android.app.Application
import org.mipt.planetshop.di.AppComponent
import org.mipt.planetshop.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }

    companion object {
        lateinit var component: AppComponent
    }
}