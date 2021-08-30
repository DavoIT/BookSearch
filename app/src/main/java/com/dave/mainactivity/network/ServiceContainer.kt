package com.dave.mainactivity.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceContainer private constructor() {
    companion object {
        val instance = ServiceContainer()
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .build()
        private val booksService = retrofit.create(BooksService::class.java)
    }

    fun getBooksService(): BooksService {
        return booksService
    }

}