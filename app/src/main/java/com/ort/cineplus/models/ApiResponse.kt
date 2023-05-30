package com.ort.cineplus.models

import com.ort.cineplus.entities.MovieX

data class ApiResponse(
    val page: Int,
    val results: MutableList<MovieX>,
    val total_pages: Int,
    val total_results: Int
)