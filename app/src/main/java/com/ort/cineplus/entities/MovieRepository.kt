package com.ort.cineplus.entities

import com.ort.cineplus.models.ApiResponse
import com.ort.cineplus.models.MovieDbClient
import retrofit2.Call
import retrofit2.awaitResponse


class MovieRepository {
    suspend fun setPopularMovies(): MutableList<MovieX>{
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("movie/popular?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }
    suspend fun setUpcomingMovies(): MutableList<MovieX>{
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("movie/upcoming?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=2")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }
    suspend fun setSimilarMovies(id: Int): MutableList<MovieX>{
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("movie/${id}/similar?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }

    suspend fun searchMoviesByName(query: String, page: Int): MutableList<MovieX> {
        val call: Call<ApiResponse> = MovieDbClient.getService().getMovies("search/movie?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&query=${query}&page=${page}&include_adult=false")
        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            response.body()?.results ?: mutableListOf()
        } else {
            mutableListOf()
        }

    }
}
