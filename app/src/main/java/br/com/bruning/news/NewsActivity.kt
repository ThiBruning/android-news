package br.com.bruning.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.bruning.news.item.ArticleItem
import br.com.bruning.news.models.Article
import br.com.bruning.news.models.NewsResponse
import br.com.bruning.news.service.NewsService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        listArticles()
    }

    private fun listArticles()
    {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance("https://newsapi.org/v2/")
        val service = retrofitInstance.create(NewsService::class.java)
        val callback = service.getNews("", "br")

        callback.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("RESPOSTA", t.message, t)
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                loadRecycler(response.body()?.articles)
            }
        })
    }

    private fun loadRecycler(articles: List<Article>?)
    {
        val adapter = GroupAdapter<ViewHolder>()
        articles?.forEach {articles ->
            adapter.add(ArticleItem(articles))
        }

        recycler_articles.adapter = adapter
    }

}
// 5954b9e550614264948df7972d254e0e



