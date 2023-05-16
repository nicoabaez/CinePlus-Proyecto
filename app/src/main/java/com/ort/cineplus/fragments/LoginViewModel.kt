package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.User

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var db = Firebase.firestore
    private var users = mutableListOf<User>()

    fun login (username: String, pass:String) : Boolean{
       db.collection("Users")
            .get().addOnSuccessListener { result ->
                 for(results in result){
                     users.add(results.toObject())
                 }
            }
        val userSearched = users.firstOrNull{it.email == username}
        if(userSearched != null){
            return true;
        }
        return false;
    }
}