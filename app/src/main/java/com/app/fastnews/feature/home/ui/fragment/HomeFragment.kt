package com.app.fastnews.feature.home.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fastnews.R
import com.app.fastnews.databinding.FragmentHomeBinding
import com.app.fastnews.feature.home.ui.adapter.CategoryAdapter
import com.app.fastnews.feature.home.ui.adapter.NewsAdapter
import com.app.fastnews.feature.home.ui.vm.NewsViewModel
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment(), CategoryAdapter.CategoryClickListener {
  private var mBinding: FragmentHomeBinding? = null
  private var mViewModel: NewsViewModel? = null
  private val SORT_BY: String = "popularity"
  private val newsAdapter by lazy { NewsAdapter(context) }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mBinding = DataBindingUtil.inflate(
      inflater, R.layout.fragment_home,
      container, false
    )
    return mBinding.let { it?.root }
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupView()
    setupObserver()
  }
  
  @SuppressLint("SimpleDateFormat")
  private fun getCurrentDate(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
  }
  
  private fun setupObserver() {
    mViewModel?.mTopHeadlineData?.observe(viewLifecycleOwner) {
      it.let {
        mBinding?.rvNewsList?.scrollToPosition(0)
        newsAdapter.addItems(it?.newsList)
      }
    }
    mViewModel?.mArticlesData?.observe(viewLifecycleOwner) {
      it.let {
        mBinding?.rvNewsList?.scrollToPosition(0)
        newsAdapter.addItems(it?.newsList)
      }
    }
  }
  
  private fun setupView() {
    //initialize viewModel
    mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    
    //initialize recyclerView
    mBinding?.categoryList?.initRecyclerView(this)
    mBinding?.rvNewsList?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    mBinding?.rvNewsList?.adapter = newsAdapter
    
    //get articles news
    mViewModel?.getTopHeadlines("in")
  }
  
  override fun onTabChange(tabId: Int?) {
    when (tabId) {
      1 -> mViewModel?.getTopHeadlines("in")
      2 -> mViewModel?.getArticles("business", getCurrentDate(), SORT_BY)
      3 -> mViewModel?.getArticles("apple", getCurrentDate(), SORT_BY)
      4 -> mViewModel?.getArticles("tesla", getCurrentDate(), SORT_BY)
      5 -> mViewModel?.getArticles("technology", getCurrentDate(), SORT_BY)
    }
  }
  
}