package com.ort.cineplus.fragments

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ort.cineplus.entities.User
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var auth: FirebaseAuth = FirebaseAuth.getInstance();
    var _userName = MutableLiveData<String>()
    val user : LiveData<String> get() = _userName
    var database = Firebase.firestore
    private val storageRef = Firebase.storage.reference
    private val currentUser = Firebase.auth.currentUser

    fun logout(){
        auth.signOut()
    }

    fun getUserDatabase(): Unit{
        viewModelScope.launch {
            try {
                database.collection("Users").document(auth.uid.toString())
                    .get().addOnSuccessListener { document ->
                        if (document != null) {
                            var user = document.toObject<User>()
                            _userName.value = user?.name
                        } else Log.d(TAG, "No such document")
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }

            }catch(e: Exception) {
                //manejo de errores generales

            }
        }
    }

    fun changeImageProfile(activity: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    fun saveImageToFirebaseStorage(imageUri: Uri) {
        val profileImageRef = storageRef.child("profile_images/${currentUser?.uid}.jpg")
        Log.d(TAG, "saveImageToFirebaseStorage: called")

        val uploadTask = profileImageRef.putFile(imageUri)
        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // La imagen se ha subido correctamente
                profileImageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Aquí puedes guardar la URL de descarga en Firestore o actualizar otros datos del usuario
                }
            } else {
                // Ha ocurrido un error al subir la imagen
                val exception = task.exception
            }
        }
    }

}