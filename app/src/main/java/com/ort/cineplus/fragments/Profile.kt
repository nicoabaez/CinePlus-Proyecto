package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.R
import com.ort.cineplus.activities.LoginActivity
import com.ort.cineplus.activities.MainActivity

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var titleInfo: TextView
    lateinit var btnGoToLogin: Button
    lateinit var btnLogout: Button
    private val user = Firebase.auth.currentUser
    private val goToMovieList = ProfileDirections.profileGoToMovieList()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        titleInfo = v.findViewById(R.id.lblMsgNoLogged)
        btnGoToLogin = v.findViewById(R.id.btnGoToLogin)
        btnLogout = v.findViewById(R.id.btnLogout)

        if(user == null){
            showProfileNoLogged()
        }else {
            showProfileLogged()
        }

        btnLogout.setOnClickListener(){
            viewModel.logout();
            findNavController().navigate(goToMovieList)
        }

        btnGoToLogin.setOnClickListener(){
            goToLoggin()
        }

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private fun showProfileLogged(){
        btnLogout.visibility = View.VISIBLE
        titleInfo.visibility = View.GONE;
        btnGoToLogin.visibility = View.GONE;
    }

    private fun showProfileNoLogged(){
        titleInfo.visibility = View.VISIBLE;
        btnGoToLogin.visibility = View.VISIBLE;
        btnLogout.visibility = View.GONE;
    }


    private fun goToLoggin(){
        startActivity(Intent(activity, LoginActivity::class.java))
    }

}