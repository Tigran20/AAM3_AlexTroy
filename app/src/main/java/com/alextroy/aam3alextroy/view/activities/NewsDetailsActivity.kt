package com.alextroy.aam3alextroy.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alextroy.aam3alextroy.R
import com.alextroy.aam3alextroy.model.NewsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_detail.*
import java.text.SimpleDateFormat
import java.util.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var fullText: TextView
    private lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)

        init()
        getData()
    }

    private fun init() {
        image = news_image_detail
        title = news_title_detail
        fullText = news_full_text_detail
        date = news_published_date_detail
    }

    private fun getData() {
        val data: NewsItem = intent.getSerializableExtra(KEY_NEWS_ITEM) as NewsItem
        Glide.with(this).load(data.imageUrl).into(image)
        title.text = data.title
        fullText.text = data.fullText
        date.text = dateAgo(data.publishDate)
    }

    companion object {
        private const val KEY_NEWS_ITEM = "news_key_item"

        fun newIntent(context: Context, news: NewsItem) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            val bundle = Bundle()

            bundle.putSerializable(KEY_NEWS_ITEM, news)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    private fun dateAgo(date: Date): String {
        val time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val format = SimpleDateFormat("HH", Locale.getDefault()).format(date).toInt()
        val format2 = SimpleDateFormat("HH:mm a", Locale.getDefault()).format(date)
        val temp = time - format
        return temp.toString() + " hr. ago, " + format2
    }
}