package com.ort.cineplus.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.cineplus.entities.MovieX
import com.ort.cineplus.entities.PopularMoviesRepository
import kotlinx.coroutines.launch

class MovieListFragmentViewModel : ViewModel() {

    val repository: PopularMoviesRepository = PopularMoviesRepository()
    private val _movieList = MutableLiveData<MutableList<MovieX>>()
    val movieList: LiveData<MutableList<MovieX>> get() = _movieList
    
    init {
        loadPopularMovies()
    }
    private fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getPopularMovies()
                _movieList.value = movies
            } catch (e: Exception) {
                // Manejo de errores generales
            }
        }
    }
    fun searchMoviesByName(query: String) {
        viewModelScope.launch {
            try {
                val movies = repository.searchMoviesByName(query)
                _movieList.value = movies
            } catch (e: Exception) {
                // Manejo de errores generales
            }
        }
    }
}