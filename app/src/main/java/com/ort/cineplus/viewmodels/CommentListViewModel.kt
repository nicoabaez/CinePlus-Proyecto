package com.ort.cineplus.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.Comment

class CommentListViewModel : ViewModel() {
    var database = Firebase.firestore
    private var commentList : MutableList<Comment> = mutableListOf()

    fun getCommentsByMovieId(movieId: Int, callback:(result : MutableList<Comment>) -> Unit){
        database.collection("Comments")
            .whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    commentList.add(document.toObject<Comment>())
                }
                callback(commentList)
            }
            .addOnFailureListener { exception ->
                Log.w("Main Activity", "Error getting documents.", exception)
            }
    }
}