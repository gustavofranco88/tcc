package com.example.agenda.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "https://d74e-2001-1284-f013-3585-a931-6133-3fe1-4632.sa.ngrok.io/"

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
