package com.ort.cineplus.entities

import android.annotation.SuppressLint
import android.util.Log
import com.ort.cineplus.models.MovieDbClient
import com.ort.cineplus.models.PopularMovies
import com.ort.cineplus.models.TheMovieDbService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularMoviesRepository {
    private var categoria: String = "Películas Populares"
    private var client: MovieDbClient = MovieDbClient
    var movies: MutableList<MovieX> = mutableListOf()
    init {
        addPopularMovies("movie/popular?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")
    }

    fun getCategoria(): String{
        return this.categoria
    }

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(client.urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun addPopularMovies(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val service = retrofit.create(TheMovieDbService::class.java)
            val call: Call<PopularMovies> = service.getPopularMovies(url)

            call.enqueue(object : Callback<PopularMovies> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                    if (response.isSuccessful) {
                        val respuestaPeliculas = response.body()?.results ?: emptyList()
                        movies.clear()
                        movies.addAll(respuestaPeliculas)
                        Log.d("DENTRO DEL REPOSITORY", "PELICULAS AÑADIDAS2 : $respuestaPeliculas")
                        //adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                    //Toast.makeText(context, "HA OCURRIDO UN ERROR:", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

