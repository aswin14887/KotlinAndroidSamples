package com.divallion.recyclerviewrest

import android.app.Application
import com.divallion.recyclerviewrest.dagger.AppComponent
import com.divallion.recyclerviewrest.dagger.AppModule
import com.divallion.recyclerviewrest.dagger.DaggerAppComponent

class MainApplication : Application() {

    private val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun appComponent() : AppComponent = appComponent
}
