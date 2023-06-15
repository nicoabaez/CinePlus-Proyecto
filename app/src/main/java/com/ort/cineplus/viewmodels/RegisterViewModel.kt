package com.ort.cineplus.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {
    var database = Firebase.firestore
    private val auth: FirebaseAuth = FirebaseAuth.getInstance();


    fun authRegister(user: User){
            auth.createUserWithEmailAndPassword(user.email, user.pass)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        this.addUser(user)

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
    }

     fun addUser(user: User){
        database.collection("Users").document(auth.uid.toString())
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }


    }



    private suspend fun emailAlreadyRegistered(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            val auth = FirebaseAuth.getInstance()
            try {
                val result = auth.fetchSignInMethodsForEmail(email).await()
                val signInMethods = result.signInMethods
                signInMethods != null && signInMethods.isNotEmpty()
            } catch (e: Exception) {

                false
            }
        }
    }

    suspend fun emailIsNotUsed(email: EditText): Boolean {
        var boolean = false
        val emailRegistered = emailAlreadyRegistered(email.text.toString())
        if (isNotEmpty(email)){
            if (emailRegistered) {
                showError(email, "Email ya registrado")
                clearInput(email)

            } else {
                boolean = true
            }
        }
        return boolean
    }

    fun checkPass(confirmPass: EditText, pass: EditText): Boolean {
        var boolean = false
        if (isNotEmpty(pass) && isNotEmpty(confirmPass)) {
            if (pass.text.length >= 6) {
                if (confirmPass.text.toString().equals(pass.text.toString())) {
                    boolean = true
                } else {
                    showError(confirmPass, "Las contraseñas no coinciden")

                }

            } else {
                showError(confirmPass, "La contraseña debe tener 6 o mas caracteres")
            }
        }

        return boolean
    }
    private fun clearInput(editText: EditText) {
        editText.setText("")
    }

    private fun showError(editText: EditText, s: String) {
        editText.error = s
        editText.clearFocus()
    }

    private fun isNotEmpty(editText: EditText): Boolean {
        var valid = true
        if(editText.text.isEmpty()){
            showError(editText,"Campo requerido" )
            valid = false
        }
        return valid
    }

}
