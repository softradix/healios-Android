package com.softradix.healios.network

import com.softradix.healios.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var mRetrofit: Retrofit? = null

    //creating retrofit object
    init {
        mRetrofit =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
    }


    // Creating OkHttpclient Object
    private fun getClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val interceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        return OkHttpClient().newBuilder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

            .build()
    }


    fun createApiService(): ApiService? = mRetrofit?.create(ApiService::class.java)

}