package com.divallion.recyclerviewrest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(@SerializedName("status") @Expose val status: String,
                @SerializedName("source") @Expose val source: String,
                @SerializedName("articles") @Expose val articles: List<Articles>)


data class Articles(@SerializedName("author") @Expose val author: String,
                    @SerializedName("title") @Expose val title: String,
                    @SerializedName("description") @Expose val description: String,
                    @SerializedName("url") @Expose val url: String,
                    @SerializedName("urlToImage") @Expose val urlToImage: String,
                    @SerializedName("publishedAt") @Expose val publishedAt: String)