package br.com.bruning.news.models

import com.google.gson.annotations.SerializedName

class NewsResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: List<Article>
)

