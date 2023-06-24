package com.example.newsapplication.news.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapplication.databinding.FragmentNewsAdditionalDataBinding
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.utils.DateUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsAdditionalDataFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNewsAdditionalDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsModel: NewsModel

    private val navArgs: NewsAdditionalDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsAdditionalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        newsModel = navArgs.news
        binding.news = newsModel
        binding.tvPublishedAt.text = DateUtils.formatDate(newsModel.publishedAt)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}