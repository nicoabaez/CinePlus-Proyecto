package com.ort.cineplus.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClient {
    val urlBase = "https://api.themoviedb.org/3/"
    val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : TheMovieDbService = retrofit.create(TheMovieDbService::class.java)

}