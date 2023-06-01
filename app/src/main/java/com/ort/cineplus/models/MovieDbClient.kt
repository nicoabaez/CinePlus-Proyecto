package com.ort.cineplus.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClient {
    private const val baseUrl = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): TheMovieDbService {
        return retrofit.create(TheMovieDbService::class.java)
    }
    private fun getBaseUrl(): String {
        return baseUrl
    }
}