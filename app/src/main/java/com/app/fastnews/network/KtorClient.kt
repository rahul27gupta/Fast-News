package com.app.fastnews.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorClient {
  companion object {
    private const val TIME_OUT = 60_000
    private const val TAG_HTTP_STATUS = "HTTP status ::"
    private const val TAG_KTOR_LOG = "Logger Ktor ::"
    
    fun ktorClient() = HttpClient(Android) {
      install(JsonFeature) {
        serializer = GsonSerializer()
      }
      
      install(Logging) {
        logger = object : Logger {
          override fun log(message: String) {
            Log.i(TAG_KTOR_LOG, message)
          }
        }
        level = LogLevel.ALL
      }
      
      install(ResponseObserver) {
        onResponse { response ->
          Log.i(TAG_HTTP_STATUS, "${response.status.value}")
        }
      }
      
      install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        parameter("apiKey", "api_key")
      }
      
      engine {
        connectTimeout = TIME_OUT
        socketTimeout = TIME_OUT
      }
    }
  }
}
