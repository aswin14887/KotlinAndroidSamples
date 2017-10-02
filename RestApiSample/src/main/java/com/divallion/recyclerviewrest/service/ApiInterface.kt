package com.divallion.recyclerviewrest.service

import com.divallion.recyclerviewrest.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("articles")
    fun getNews(@Query("source") src: String,
                @Query("sortBy") sort: String,
                @Query("apiKey") key: String) : Call<News>

}