package br.com.bruning.news.item

import android.content.Intent
import android.net.Uri
import br.com.bruning.news.models.Article
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import br.com.bruning.news.R
import br.com.bruning.news.listener.ArticleAdapterListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*


class ArticleItem(private val article: Article, var listener: ArticleAdapterListener) : Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.article_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.txtTitle.text = article.title
        viewHolder.itemView.txtAuthor.text = article.author
        viewHolder.itemView.txtDate.text = article.publishedAt
        val target = viewHolder.itemView.imageView
        Picasso.get().load(article.urlToImage).into(target)
        viewHolder.itemView.txtMore.setOnClickListener {
            listener.articleSeeMore(article.url)
        }
    }
}
