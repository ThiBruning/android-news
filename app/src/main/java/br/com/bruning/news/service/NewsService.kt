package br.com.bruning.news.service

import br.com.bruning.news.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getNews(
        @Query("q")
        busca: String,
        @Query("country")
        pais: String,
        @Query("apiKey")
        apiKey: String = "5954b9e550614264948df7972d254e0e"
    ): Call<NewsResponse>

//    @GET()
}