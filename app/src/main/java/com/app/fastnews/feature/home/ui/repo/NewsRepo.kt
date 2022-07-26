package com.app.fastnews.feature.home.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.fastnews.feature.home.ui.bean.NewsResponseMain
import com.app.fastnews.network.ApiInterface
import com.app.fastnews.network.RetrofitClient
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class NewsRepo : NewsRepoInterface {
  private val mApiInterface: ApiInterface? =
    RetrofitClient.RetrofitInstance.getClient()?.create(ApiInterface::class.java)
  
  //Mutable Live Data
  private val _articlesData = MutableLiveData<NewsResponseMain?>()
  private val _topHeadlinesData = MutableLiveData<NewsResponseMain?>()
  
  //Live Data
  fun getArticlesData(): LiveData<NewsResponseMain?> = _articlesData
  fun getTopHeadlinesData(): LiveData<NewsResponseMain?> = _topHeadlinesData
  
  var job: Job? = null
  
  override suspend fun getArticles(category: String?, date: String?, sortBy: String?) {
    job = CoroutineScope(Dispatchers.IO).launch {
      val response = mApiInterface?.getArticles(
        category, date, date, sortBy
      )
      withContext(Dispatchers.Main) {
        if (response?.status == "ok") {
          _articlesData.postValue(response)
        } else {
          onError("Error : ${response?.status} ")
        }
      }
    }
  }
  
  override suspend fun getTopHeadlines(country: String?) {
    job = CoroutineScope(Dispatchers.IO).launch {
      val response = mApiInterface?.getTopHeadlines(country)
      withContext(Dispatchers.Main) {
        if (response?.status == "ok") {
          _topHeadlinesData.postValue(response)
        } else {
          onError("Error : ${response?.status} ")
        }
      }
    }
  }
  
  private fun onError(message: String) {
    Log.e("TAG", "onError: $message")
  }
  
}

interface NewsRepoInterface {
  suspend fun getArticles(category: String?, date: String?, sortBy: String?)
  suspend fun getTopHeadlines(country: String?)
}