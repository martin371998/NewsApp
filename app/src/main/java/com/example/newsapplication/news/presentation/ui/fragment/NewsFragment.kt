package com.example.newsapplication.news.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.GlobalViewModel
import com.example.newsapplication.core.extentions.gone
import com.example.newsapplication.core.extentions.safe
import com.example.newsapplication.core.extentions.shouldShowFap
import com.example.newsapplication.core.extentions.showToast
import com.example.newsapplication.core.extentions.visible
import com.example.newsapplication.core.models.DataStatus
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.news.presentation.ui.adapter.NewsAdapter
import com.example.newsapplication.news.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel by viewModels<NewsViewModel>()
    private val sharedViewModel: GlobalViewModel by activityViewModels()


    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        implementListeners()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            newsViewModel.news.collect {
                newsAdapter.setData(it)
            }
        }
        lifecycleScope.launch {
            newsViewModel.status.collect { status ->
                when (status) {
                    is DataStatus.DataError -> {
                        showLoading(false)
                        requireContext().showToast(status.exception?.message.safe())
                    }

                    DataStatus.DataLoaded -> showLoading(false)
                    DataStatus.DataLoading -> showLoading(true)
                }
            }
        }
        lifecycleScope.launch {
            sharedViewModel.search.collect {
                binding.layoutHeader.tvSearch.text = it.searchQuery
                binding.layoutHeader.ivCancel.visible()
                newsViewModel.getNewsByField(it)
            }
        }
    }

    private fun implementListeners() {
        newsAdapter.setOnClick {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToNewsAdditionalDataFragment(it)
            )
        }
        binding.fap.setOnClickListener {
            binding.rvNews.smoothScrollToPosition(0)
        }
        binding.srlNews.setOnRefreshListener {
            newsViewModel.getNews("google")
        }
        binding.rvNews.shouldShowFap {
            binding.fap.isVisible = it
        }
        binding.layoutHeader.root.setOnClickListener {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToSearchFragment()
            )
        }
        binding.layoutHeader.ivCancel.setOnClickListener {
            it.gone()
            binding.layoutHeader.tvSearch.text = null
            newsViewModel.getNews("google")
        }
    }

    private fun initViews() {
        binding.rvNews.adapter = newsAdapter
        newsViewModel.getNews("google")
    }

    private fun showLoading(isVisible: Boolean) = apply { binding.srlNews.isRefreshing = isVisible }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}