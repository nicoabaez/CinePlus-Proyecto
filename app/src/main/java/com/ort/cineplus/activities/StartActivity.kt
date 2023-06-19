package com.ort.cineplus.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ort.cineplus.R
import com.ort.cineplus.fragments.MovieListFragment

class StartActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000
    lateinit var v : MovieListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
            }
            , SPLASH_TIME_OUT)
    }


}