package com.ort.cineplus.viewmodels

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.activities.MainActivity
import com.ort.cineplus.entities.User

class LoginViewModel : ViewModel() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance();
    var userLogged = false;

    fun login (email: String, pass:String) : Boolean{
        var result = false
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    updateUser()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)

                }
            }
        if(userLogged){
            result = true;
        }
        return result;
    }

    private fun updateUser() {
       this.userLogged = true;
    }

}