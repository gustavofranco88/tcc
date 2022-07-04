package com.example.agenda.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "https://listaagendamentos.free.beeceptor.com/"

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