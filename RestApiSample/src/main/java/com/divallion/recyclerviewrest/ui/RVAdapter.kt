package com.divallion.recyclerviewrest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.divallion.recyclerviewrest.R
import com.divallion.recyclerviewrest.model.Articles
import com.divallion.recyclerviewrest.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_card_view.view.*

class RVAdapter(private val news: News?) : RecyclerView.Adapter<RVAdapter.ItemHolder>() {

    override fun getItemCount(): Int = news?.articles!!.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder
        = ItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.news_card_view, parent, false))

    override fun onBindViewHolder(holder: ItemHolder?, position: Int) {
        holder?.bindViews(news?.articles!![position])
    }

    class ItemHolder (view: View): RecyclerView.ViewHolder(view) {

        fun bindViews(article : Articles) {
            with(article) {
                Picasso.with(itemView.context).load(urlToImage).resize(350,350).into(itemView.imageView)
                itemView.headline.text = title
                itemView.description.text = description
            }
        }
    }

}