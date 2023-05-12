package com.ort.cineplus.fragments

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.User

class RegisterViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var database = Firebase.firestore

    fun registerUser(user: User){
// Add a new document with a generated ID
        database.collection("Users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


}