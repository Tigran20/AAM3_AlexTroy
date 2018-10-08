package com.alextroy.aam3alextroy.view.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alextroy.aam3alextroy.R
import com.alextroy.aam3alextroy.model.DataUtils
import com.alextroy.aam3alextroy.model.NewsItem
import com.alextroy.aam3alextroy.presenter.Contract
import com.alextroy.aam3alextroy.presenter.Presenter
import com.alextroy.aam3alextroy.view.adapter.NewsAdapter


class NewsListActivity : AppCompatActivity() {

    private lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)

        if(supportActionBar != null)
        {
            supportActionBar!!.elevation = 0F
        }

        val news = ArrayList<NewsItem>()
        news.addAll(DataUtils.generateNews())
        init(news)
    }

    private fun init(news: List<NewsItem>) {
        presenter = Presenter()

        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NewsAdapter(news, this)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv.layoutManager = LinearLayoutManager(this)
        } else {
            rv.layoutManager = GridLayoutManager(this, 2)
        }

        rv.adapter = adapter

        adapter.addAll(presenter.getDataModel())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.news_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.news_about -> {
                AboutActivity.newIntent(this)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}