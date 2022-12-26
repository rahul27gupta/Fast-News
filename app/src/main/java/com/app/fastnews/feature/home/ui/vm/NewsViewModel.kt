package com.app.fastnews.feature.home.ui.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fastnews.feature.home.ui.bean.NewsResponseMain
import com.app.fastnews.feature.home.ui.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val mRepository: NewsRepo) : ViewModel() {
  
  var mArticlesData: LiveData<NewsResponseMain?> = mRepository.getArticlesData()
  var mTopHeadlineData: LiveData<NewsResponseMain?> = mRepository.getTopHeadlinesData()
  
  fun getArticles(category: String?, date: String?, sortBy: String?) =
    viewModelScope.launch {
      mRepository.getArticles(category, date, sortBy)
    }
  
  fun getTopHeadlines(country: String?) = viewModelScope.launch {
    mRepository.getTopHeadlines(country)
  }
}


