package com.ort.cineplus.activities


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ort.cineplus.R
import com.ort.cineplus.fragments.StartAppDirections

class LoginActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        Handler().postDelayed(
            {
              startActivity(Intent(this, MainActivity::class.java))
            }
            , SPLASH_TIME_OUT)
    }
}