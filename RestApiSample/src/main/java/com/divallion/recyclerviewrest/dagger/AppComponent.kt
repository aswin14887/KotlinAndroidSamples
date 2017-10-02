package com.divallion.recyclerviewrest.dagger

import android.app.Application
import android.content.Context
import com.divallion.recyclerviewrest.ui.MainActivity
import com.divallion.recyclerviewrest.ui.presenter.NewsPresenterImpl
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(newsPresenterImpl: NewsPresenterImpl)

    @ApplicationContext
    fun context() : Context

    fun gson() : Gson
    fun client() : OkHttpClient
    fun retrofit() : Retrofit
}