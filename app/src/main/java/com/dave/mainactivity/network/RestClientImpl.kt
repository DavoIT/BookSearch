package com.dave.mainactivity.network

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

//interface RestClient {
//    fun getRetrofit(): Retrofit
//}
//
//class RestClientImpl(
//    application: Application,
//) : RestClient {
//    private companion object {
//        const val SOCKET_TIMEOUT_EXCEPTION: Long = 15
//        const val DISK_CACHE_SIZE: Long = 50 * 1024 * 1024 // 50MB
//        const val ENDPOINT = NETWORK_BASE_URL
//    }
//    private val logginInteceptor = HttpLoggingInterceptor()
//    private val restExceptionInterceptor = RestExceptionInterceptor()
//    private val okHttpClient = OkHttpClient.Builder()
//        .connectTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
//        .readTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
//        .writeTimeout(SOCKET_TIMEOUT_EXCEPTION, TimeUnit.SECONDS)
//        .addInterceptor(logginInteceptor)
//        .build()
//
//    private val retrofit = Retrofit.Builder()
//        .client(okHttpClient)
//        .baseUrl(ENDPOINT)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addInterceptor(restExceptionInterceptor )
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//
//    override fun getRetrofit(): Retrofit {
//        return retrofit
//    }
//}