package com.app.fastnews.feature.home.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.fastnews.feature.home.ui.bean.NewsResponseMain
import com.app.fastnews.network.ApiInterface
import com.app.fastnews.network.KtorClient
import com.app.fastnews.network.RetrofitClient
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsRepo : NewsRepoInterface {
  private val mApiInterface = RetrofitClient.getClient()?.create(ApiInterface::class.java)
  private val client = KtorClient.ktorClient()
  
  //Mutable Live Data
  private val _articlesData = MutableLiveData<NewsResponseMain?>()
  private val _topHeadlinesData = MutableLiveData<NewsResponseMain?>()
  
  //Live Data
  fun getArticlesData(): LiveData<NewsResponseMain?> = _articlesData
  fun getTopHeadlinesData(): LiveData<NewsResponseMain?> = _topHeadlinesData
  
  
  override suspend fun getArticles(category: String?, date: String?, sortBy: String?) {
    CoroutineScope(Dispatchers.IO).launch {
      val response = mApiInterface?.getArticles(
        category, date, date, sortBy
      )
      if (response?.status == "ok") {
        _articlesData.postValue(response)
      } else {
        onError("Error : ${response?.status} ")
      }
    }
  }
  
  override suspend fun getTopHeadlines(country: String?) {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val response = client.get<NewsResponseMain?> {
          url("https://newsapi.org/v2/top-headlines")
          parameter("country", country)
        }
        if (response?.status == "ok") {
          _topHeadlinesData.postValue(response)
        } else {
          onError("Error : ${response?.status} ")
        }
      } catch (e: Exception) {
        Log.e("TAG", "Exception:: $e ")
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