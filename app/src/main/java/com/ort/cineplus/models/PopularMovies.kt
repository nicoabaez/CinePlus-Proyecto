package com.ort.cineplus.models

import com.ort.cineplus.entities.MovieX

data class PopularMovies(
    val page: Int,
    val results: List<MovieX>,
    val total_pages: Int,
    val total_results: Int
)