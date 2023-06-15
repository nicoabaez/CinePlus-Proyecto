package com.ort.cineplus.entities

import com.ort.cineplus.models.ApiResponse
import com.ort.cineplus.models.MovieDbClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.awaitResponse


class PopularMoviesRepository {

    private lateinit var category: String

    suspend fun setPopularMovies(): MutableList<MovieX>{
        category = "Popular Movies"
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("movie/popular?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }
    suspend fun setUpcomingMovies(): MutableList<MovieX>{
        //https://api.themoviedb.org/3/movie/upcoming?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1
        category = "Upcoming Movies"
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("movie/upcoming?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }
    suspend fun searchMoviesByName(query: String): MutableList<MovieX> {
        category = "Search Results"
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("search/movie?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&query=${query}&page=1&include_adult=false")

        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }

    }

    fun getCategory(): String { return this.category }
}
