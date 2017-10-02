package com.divallion.recyclerviewrest.ui.presenter

import android.content.Context
import com.divallion.recyclerviewrest.MainApplication
import com.divallion.recyclerviewrest.R
import com.divallion.recyclerviewrest.model.News
import com.divallion.recyclerviewrest.service.ApiInterface
import com.divallion.recyclerviewrest.ui.NewsView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewsPresenterImpl(private val context: Context): NewsPresenter {

    private val app : MainApplication get() = context.applicationContext as MainApplication

    @Inject lateinit var gson : Gson
    @Inject lateinit var client : OkHttpClient
    @Inject lateinit var retrofit : Retrofit
    @Inject lateinit var apiService : ApiInterface

    private lateinit var newsView: NewsView

    override fun setNewsView(newsView: NewsView) {
        app.appComponent().inject(this)
        this.newsView = newsView
    }

    override fun getNews() {
        newsView.hideProgress()

        apiService.getNews("bbc-news","top",context.getString(R.string.key)).enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>?, t: Throwable?) {
                hideProgress()
                newsView.showError()
            }

            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                hideProgress()
                newsView.showNews(response?.body())
            }

        })
    }

    private fun hideProgress() = newsView.hideProgress()
}
