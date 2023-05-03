package com.ort.cineplus.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://developers.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service : TheMovieDbService = retrofit.create(TheMovieDbService::class.java)

    fun getRetrofit(): Retrofit? {
        return this.retrofit;
    }
    fun getService(): TheMovieDbService {
        return this.service;
    }
}