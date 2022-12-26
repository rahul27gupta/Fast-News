package com.app.fastnews.base

import com.app.fastnews.BuildConfig.BASE_URL
import com.app.fastnews.network.ApiInterface
import com.app.fastnews.network.BaseRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseDependencyModule {
  
  @Provides
  fun provideBaseUrl() = BASE_URL
  
  @Provides
  @Singleton
  fun provideRetrofitService(): Retrofit = BaseRetrofitClient.create(baseUrl = BASE_URL)
  
  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}