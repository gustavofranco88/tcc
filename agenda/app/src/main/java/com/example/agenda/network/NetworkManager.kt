package com.example.agenda.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "https://8668-2001-1284-f013-3585-e563-e218-8b8f-dbe5.sa.ngrok.io/"

object NetworkManager {

    lateinit var client: OkHttpClient

    val service: ApiServices by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(ApiServices::class.java)
    }

    fun stop() {
        client.dispatcher().cancelAll()
    }

}
