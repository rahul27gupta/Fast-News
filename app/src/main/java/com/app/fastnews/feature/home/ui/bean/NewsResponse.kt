package com.app.fastnews.feature.home.ui.bean

import com.google.gson.annotations.SerializedName

class NewsResponse {
  
  @SerializedName("author")
  var author: String? = null
  
  @SerializedName("title")
  var title: String? = null
  
  @SerializedName("url")
  var url: String? = null
  
  @SerializedName("urlToImage")
  var imgUrl: String? = null
  
}