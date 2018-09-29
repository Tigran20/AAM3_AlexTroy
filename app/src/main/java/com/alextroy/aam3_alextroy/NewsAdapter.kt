package com.alextroy.aam3_alextroy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alextroy.aam3_alextroy.model.NewsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsAdapter(val items: ArrayList<NewsItem>, val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setOnItemClickListener(listener)
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsCategory.text = items[position].category.name
        holder.newsTitle.text = items[position].title
        holder.newsPreview.text = items[position].previewText
        holder.newsDate.text = items[position].publishDate.toString()
        Glide.with(context).load(items[position].imageUrl).into(holder.newsImage)

        holder.cardView.setOnClickListener {
            listener.onClick(it, data = items[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsCategory = view.news_category!!
        val newsTitle = view.news_title!!
        val newsPreview = view.news_preview_text!!
        val newsDate = view.news_published_date!!
        val newsImage = view.news_image!!
        val cardView = view.card_view!!
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: NewsItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}