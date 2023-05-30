package com.ort.cineplus.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface TheMovieDbService {
    @GET
    fun getMovies(@Url url:String): Call<ApiResponse>
}