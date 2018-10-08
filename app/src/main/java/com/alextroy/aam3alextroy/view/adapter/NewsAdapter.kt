package com.alextroy.aam3alextroy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alextroy.aam3alextroy.R
import com.alextroy.aam3alextroy.model.NewsItem
import com.alextroy.aam3alextroy.view.activities.NewsDetailsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_list_item.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private var items: List<NewsItem>, private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = items[position]
        holder.newsCategory.text = news.category.name
        holder.newsTitle.text = news.title
        holder.newsPreview.text = news.previewText
        holder.newsDate.text = dateAgo(news.publishDate)

        Glide.with(context).load(news.imageUrl).into(holder.newsImage)

        holder.cardView.setOnClickListener {
            NewsDetailsActivity.newIntent(context, news)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsCategory = view.news_category!!
        val newsTitle = view.news_title_detail!!
        val newsPreview = view.news_preview_text!!
        val newsDate = view.news_published_date_detail!!
        val newsImage = view.news_image_detail!!
        val cardView = view.card_view!!

    }

    fun addAll(list: List<NewsItem>) {
        items = list
    }

    private fun dateAgo(data: Date?): String {
        val prettyTime = PrettyTime()
        return prettyTime.format(data)
    }
}