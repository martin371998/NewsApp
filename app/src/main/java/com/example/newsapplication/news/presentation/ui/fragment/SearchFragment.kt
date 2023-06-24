package com.example.newsapplication.news.presentation.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.newsapplication.main.presentation.viewmodel.GlobalViewModel
import com.example.newsapplication.core.extentions.safe
import com.example.newsapplication.databinding.FragmentSearchBinding
import com.example.newsapplication.news.data.model.SearchModel
import com.example.newsapplication.news.data.model.SearchType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: GlobalViewModel by activityViewModels()

    private val customerList: List<String> by lazy { SearchType.values().map { it.name } }

    private var searchModel: SearchModel = SearchModel(SearchType.TITLE, "")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        implementListeners()
    }


    private fun initViews() {
        initSpinner()
    }

    private fun implementListeners() {
        binding.actType.setOnItemClickListener { _, _, position, _ ->
            searchModel.type = SearchType.valueOf(customerList[position])
        }
        binding.btnSearch.setOnClickListener {
            searchModel.searchQuery = binding.etSearch.text?.trim()?.toString().safe()
            sharedViewModel.sendSearchEvent(searchModel)
            dismiss()
        }
    }

    private fun initSpinner() {

        val adapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, customerList)
        binding.actType.setAdapter(adapter)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}