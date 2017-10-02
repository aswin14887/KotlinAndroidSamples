package com.divallion.recyclerviewrest.dagger

import android.app.Application
import android.content.Context
import com.divallion.recyclerviewrest.service.ApiInterface
import com.divallion.recyclerviewrest.R
import com.divallion.recyclerviewrest.ui.presenter.NewsPresenter
import com.divallion.recyclerviewrest.ui.presenter.NewsPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


@Module
class AppModule(application: Application) {

    private val mApplication : Application = application

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext() : Context = mApplication

    @Singleton
    @Provides
    fun providesGson(): Gson {
        val builder = GsonBuilder()
        builder.serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
        return builder.create()
    }

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson, client: OkHttpClient) =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .baseUrl(mApplication.getString(R.string.base_url))
                    .build()

    @Singleton
    @Provides
    fun providesPresenter() : NewsPresenter = NewsPresenterImpl(mApplication)

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)!!

}