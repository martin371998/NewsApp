package com.example.newsapplication.news.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapplication.databinding.LayoutNewsBinding
import com.example.newsapplication.news.data.model.NewsModel
import javax.inject.Inject

class NewsAdapter @Inject constructor() :
    RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>() {

    private val allNews: ArrayList<NewsModel> = arrayListOf()
    private var onClick: ((NewsModel) -> Unit)? = null
    fun setData(news: List<NewsModel>) {
        allNews.clear()
        allNews.addAll(news)
        notifyDataSetChanged()
    }

    fun setOnClick(listener: (NewsModel) -> Unit) {
        this.onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder =
        NewsAdapterViewHolder(
            LayoutNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = allNews.size

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        holder.bind(allNews[position])
    }

    inner class NewsAdapterViewHolder(
        private var binding: LayoutNewsBinding
    ) : ViewHolder(binding.root) {
        fun bind(newsModel: NewsModel) {
            binding.news = newsModel
            binding.root.setOnClickListener { onClick?.invoke(newsModel) }
        }
    }

}