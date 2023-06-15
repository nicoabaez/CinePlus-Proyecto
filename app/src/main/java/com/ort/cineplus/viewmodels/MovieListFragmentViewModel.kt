package com.ort.cineplus.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.cineplus.entities.MovieX
import com.ort.cineplus.entities.MovieRepository
import kotlinx.coroutines.launch

class MovieListFragmentViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()

    private val _movieList = MutableLiveData<MutableList<MovieX>>()
    val popularMovieList: LiveData<MutableList<MovieX>> get() = _movieList

    private val _movieList2 = MutableLiveData<MutableList<MovieX>>()
    val upcomingMovieList: LiveData<MutableList<MovieX>> get() = _movieList2

    private val _movieList3 = MutableLiveData<MutableList<MovieX>>()
    val searchedMovies: LiveData<MutableList<MovieX>> get() = _movieList3


    init {
        loadPopularMovies()
        loadUpcomingMovies()
    }
    private fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                _movieList.value = repository.setPopularMovies()
            } catch (e: Exception) {
                Log.d("Error en loadPopularMovies()", e.message.toString())
            }
        }
    }
    private fun loadUpcomingMovies(){
        viewModelScope.launch {
            try {
                _movieList2.value = repository.setUpcomingMovies()
            } catch (e: Exception) {
                Log.d("Error en loadUpcomingMovies()", e.message.toString())
            }
        }
    }
    fun searchMoviesByName(query: String) {
        viewModelScope.launch {
            try {
                _movieList3.value = repository.searchMoviesByName(query,1)
            } catch (e: Exception) {
                Log.d("Error en searchMoviesByName()", e.message.toString())
            }
        }
    }
}