package com.laughcraft.android.companies.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    const val API_ENDPOINT = "http://megakohz.bget.ru/test_task/"

    fun <S> create(clazz: Class<S>): S{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
        
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().create()
            )).build()
            .create(clazz)
    }
}