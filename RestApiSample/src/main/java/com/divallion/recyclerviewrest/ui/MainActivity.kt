package com.divallion.recyclerviewrest.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.divallion.recyclerviewrest.MainApplication
import com.divallion.recyclerviewrest.R
import com.divallion.recyclerviewrest.model.News
import com.divallion.recyclerviewrest.service.ApiInterface
import com.divallion.recyclerviewrest.ui.presenter.NewsPresenter
import com.divallion.recyclerviewrest.ui.presenter.NewsPresenterImpl
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_layout.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NewsView {

    private val app: MainApplication get() = application as MainApplication

    @Inject lateinit var newsPresenter : NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        app.appComponent().inject(this)
        recyclerList?.layoutManager = LinearLayoutManager(this)

        newsPresenter.setNewsView(this)
        newsPresenter.getNews()

    }

    override fun showError() {
        Toast.makeText(app,"Something went wrong!!!",Toast.LENGTH_LONG).show()
    }

    override fun showNews(news: News?) {
        recyclerList.adapter = RVAdapter(news!!)
    }

    override fun showProgress() {
        loading?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loading?.visibility = View.INVISIBLE
    }

}
