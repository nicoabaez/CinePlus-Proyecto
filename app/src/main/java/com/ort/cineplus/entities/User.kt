package com.ort.cineplus.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class User (
    val name: String,
    val email: String,
    val pass: String,
    val imgProfile: String


        ): Parcelable {
    constructor(): this("", "", "", "")
}