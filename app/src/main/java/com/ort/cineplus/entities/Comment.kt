package com.ort.cineplus.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Comment (
    val id: Int,
    val userName: String,
    val movieId: Int,
    val detailComment: String,
//    val valoration: Int
): Parcelable {

}