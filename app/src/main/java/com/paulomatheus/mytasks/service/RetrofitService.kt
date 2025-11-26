package com.paulomatheus.mytasks.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService() {
    companion object{ //constante em uma classe kotlin
        val BASE_URL = "http://10.0.24.67:8080"

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createClient())
                .addConverterFactory(createConverter())
                .build()
        }

        private fun createClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private fun createConverter(): Converter.Factory {
            return GsonConverterFactory.create()
        }
    }
}