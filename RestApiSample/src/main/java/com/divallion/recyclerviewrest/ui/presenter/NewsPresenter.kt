package com.divallion.recyclerviewrest.ui.presenter

import com.divallion.recyclerviewrest.ui.NewsView

interface NewsPresenter {
    fun setNewsView(newsView: NewsView)
    fun getNews()
}