package com.ort.cineplus.fragments

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.User

class RegisterViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var database = Firebase.firestore
    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private var users = mutableListOf<User>()

    fun authRegister(user: User): Boolean{
        var result = false;
        if(!checkEmail(user.email)){
            auth.createUserWithEmailAndPassword(user.email, user.pass)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        database.collection("Users")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
            result = true
        }
        return result;
    }

    fun checkPass(pass: String, confirmPass: String): Boolean {
        var result = false;
        if(pass == confirmPass){
            if(pass.length >= 6){
                result = true;
            }
        }
        return result;
    }

    fun checkEmail(email: String) : Boolean {
        var result = false;
        database.collection("Users")
            .get().addOnSuccessListener { documents ->
                for(document in documents){
                    users.add(document.toObject())
                }
            }

        val userSearched = users.firstOrNull { it.email == email }
        Log.d("usuario:", "usuario encontrado: $userSearched")
        if(userSearched != null){
            result = true ;
        }
        return result;
    }

}