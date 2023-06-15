package com.ort.cineplus.viewmodels

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
    private val _movieList2 = MutableLiveData<MutableList<MovieX>>()
    val movieList2: LiveData<MutableList<MovieX>> get() = _movieList2

    init {
        loadPopularMovies()
        loadUpcomingMovies()
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
    private fun loadUpcomingMovies(){
        viewModelScope.launch {
            try {
                val movies = repository.getUpcomingMovies()
                _movieList2.value = movies
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