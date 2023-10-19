package com.ort.cineplus.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var userLogged = false

    fun login (email: String, pass:String) : Boolean{
        var result = false
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener{ task ->
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
            result = true
        }
        return result
    }
    private fun updateUser() {
       this.userLogged = true
    }

}