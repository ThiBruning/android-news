package br.com.bruning.news.item

import br.com.bruning.news.models.Article
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import br.com.bruning.news.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*


class ArticleItem(val article: Article) : Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.article_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.txtTitle.text = article.title
        val target = viewHolder.itemView.imageView
        Picasso.get().load(article.urlToImage).into(target)
    }
}
