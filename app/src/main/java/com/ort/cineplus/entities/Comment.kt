package com.ort.cineplus.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Comment (
    val userEmail: String,
    val movieId: Int,
    val detailComment: String
): Parcelable {
    constructor(): this("",0,"")
}