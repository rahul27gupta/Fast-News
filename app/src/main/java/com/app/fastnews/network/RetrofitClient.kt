package com.app.fastnews.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
  
  companion object {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://newsapi.org/v2/"
    
    @JvmStatic
    fun getClient(): Retrofit? {
      // Retrofit Builder
      val builder = OkHttpClient.Builder()
      builder.connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .addInterceptor { chain ->
          return@addInterceptor addApiKeyToRequests(chain)
        }
      
      val okHttpClient = builder.build()
      if (retrofit == null) {
        retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .client(okHttpClient)
          .build()
      }
      return retrofit
    }
    
    private fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
      val request = chain.request().newBuilder()
      val originalHttpUrl = chain.request().url
      val newUrl = originalHttpUrl.newBuilder()
        .addQueryParameter("apiKey", "api_key").build()
      request.url(newUrl)
      return chain.proceed(request.build())
    }
  }
}