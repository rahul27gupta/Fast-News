package com.app.fastnews.feature.home.ui.bean

import com.google.gson.annotations.SerializedName

class NewsResponseMain {
  @SerializedName("status")
  var status: String? = null
  
  @SerializedName("totalResults")
  var totalResults: Int? = null
  
  @SerializedName("articles")
  var newsList: ArrayList<NewsResponse>? = null
}