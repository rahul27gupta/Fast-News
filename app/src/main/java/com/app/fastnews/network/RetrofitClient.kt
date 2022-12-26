package com.app.fastnews.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BaseRetrofitClient {
  
  fun create(baseUrl: String): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(
        OkHttpClient.Builder()
          .connectTimeout(10, TimeUnit.SECONDS)
          .readTimeout(15, TimeUnit.SECONDS)
          .writeTimeout(90, TimeUnit.SECONDS)
          .addInterceptor { chain ->
            return@addInterceptor addApiKeyToRequests(chain)
          }.build()
      )
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  
  private fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
    val request = chain.request().newBuilder()
    val originalHttpUrl = chain.request().url
    val newUrl = originalHttpUrl.newBuilder()
      .addQueryParameter("apiKey", "0c67b4e2eb6a47c1ba65dc6455441c0c").build()
    request.url(newUrl)
    return chain.proceed(request.build())
  }
}
