package br.com.bruning.news

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
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
import androidx.core.os.ConfigurationCompat

class NewsActivity : AppCompatActivity() {

    private val retrofitInstance = RetrofitInstance.getRetrofitInstance("https://newsapi.org/v2/")
    private val service = retrofitInstance.create(NewsService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        listArticles()
        editSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Toast.makeText(this@NewsActivity, s.toString(), Toast.LENGTH_LONG).show()
                searchArticles(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun listArticles()
    {
        val locale = getLocale()
        val callback = service.getNews("", "br")

        callback.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("FAILURELIST", t.message, t)
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

    private fun searchArticles(word: String) {
        val callback = service.getNews(word, "br")
        callback.enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("FAILIRESEARCH", t.message, t)
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                loadRecycler(response.body()?.articles)
            }
        })
    }

    private fun getLocale(): String
    {
        val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)
        return locale.country
    }

}
// 5954b9e550614264948df7972d254e0e



