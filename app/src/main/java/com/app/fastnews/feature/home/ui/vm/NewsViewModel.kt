package com.app.fastnews.feature.home.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fastnews.feature.home.ui.bean.NewsResponseMain
import com.app.fastnews.feature.home.ui.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
  private val mRepository = NewsRepo()
  
  var mArticlesData: LiveData<NewsResponseMain?> = mRepository.getArticlesData()
  var mTopHeadlineData: LiveData<NewsResponseMain?> = mRepository.getTopHeadlinesData()
  
  fun getArticles(category: String?, date: String?, sortBy: String?) =
    viewModelScope.launch(Dispatchers.IO) {
    mRepository.getArticles(category, date, sortBy)
  }
  
  fun getTopHeadlines(country: String?) = viewModelScope.launch(Dispatchers.IO) {
    mRepository.getTopHeadlines(country)
  }
}


