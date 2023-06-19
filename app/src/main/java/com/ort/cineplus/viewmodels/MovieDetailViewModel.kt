package com.ort.cineplus.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.cineplus.entities.MovieRepository
import com.ort.cineplus.entities.MovieX
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val repository: MovieRepository = MovieRepository()

    private val _movieList = MutableLiveData<MutableList<MovieX>>()
    val similarMovieList: LiveData<MutableList<MovieX>> get() = _movieList

    fun loadSimilarMovies(id: Int) {
        viewModelScope.launch {
            try {
                _movieList.value = repository.setSimilarMovies(id)
            } catch (e: Exception) {
                Log.d("Error en loadPopularMovies()", e.message.toString())
            }
        }
    }
}