package com.ort.cineplus.fragments

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProfileViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var auth: FirebaseAuth = FirebaseAuth.getInstance();
    lateinit var user: User
    var database = Firebase.firestore

    fun logout(){
        auth.signOut()
    }

    fun getUser(): User{

       database.collection("Users").document(auth.uid.toString())
           .get().addOnSuccessListener { document ->
               if (document != null) {
                   user = document.toObject<User>()!!
               } else Log.d(TAG, "No such document")
           }
           .addOnFailureListener { exception ->
               Log.d(TAG, "get failed with ", exception)
           }
        return user
    }

}