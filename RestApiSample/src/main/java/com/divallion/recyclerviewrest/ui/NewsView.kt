package com.divallion.recyclerviewrest.ui

import com.divallion.recyclerviewrest.model.News

interface NewsView{
    fun showError()
    fun showNews(news: News?)
    fun showProgress()
    fun hideProgress()
}