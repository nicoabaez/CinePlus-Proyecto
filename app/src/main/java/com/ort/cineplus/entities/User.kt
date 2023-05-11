package com.ort.cineplus.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    val id: Int,
    val name: String,
    val email: String,
    val pass: String,
    val favouriteList: MutableList<MovieX>
        ): Parcelable {
}