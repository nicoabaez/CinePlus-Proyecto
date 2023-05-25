package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.R

class Profile : Fragment() {

    companion object {
        fun newInstance() = Profile()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var userEmail: TextView;
    lateinit var btn: Button;
    val user = Firebase.auth.currentUser

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_profile, container, false)
        userEmail = v.findViewById(R.id.lblUserEmail)
        btn = v.findViewById(R.id.btnGoToLoggin)

        btn.setOnClickListener(){
            userEmail.text = user?.email.toString();
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}