package com.ort.cineplus.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.Comment
import com.ort.cineplus.entities.User

class CommentCreateViewModel : ViewModel() {

    var database = Firebase.firestore
    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    fun postComment(userEmail: String, movieId: Int, commentDescription: String): Boolean{
        var result = false;
        val comment = Comment(
            auth.currentUser?.email.toString(),
            movieId,
            commentDescription
        )

        database.collection("Comments").document()
            .set(comment)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference}")
                result = true;
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

        return result;
    }

    fun checkComment(comment: String): Boolean {
        var result = false;

        if(comment.length >= 1){
            result = true;
        }

        return result;
    }
}