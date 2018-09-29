package com.alextroy.aam3_alextroy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alextroy.aam3_alextroy.model.DataUtils
import com.alextroy.aam3_alextroy.model.NewsItem


class NewsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)

        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.layoutManager = GridLayoutManager(this, 1)

        val news = ArrayList<NewsItem>()
        news.addAll(DataUtils().generateNews())


        val adapter = NewsAdapter(news, this)

        val dec = NewsItemDecoration(20, 1)
        rv.addItemDecoration(dec)

        rv.adapter = adapter

        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onClick(view: View, data: NewsItem) {
                val intent = AboutActivity.newIntent(this@NewsListActivity)
                startActivity(intent)
            }
        })
    }
}