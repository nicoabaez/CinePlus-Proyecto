package com.ort.cineplus.fragments

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

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var userEmail: TextView
    lateinit var btnShowUser: Button
    lateinit var btnGoToLogin: Button
    lateinit var btnGoToRegister: Button
    val user = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        userEmail = v.findViewById(R.id.lblUserEmail)
        btnShowUser = v.findViewById(R.id.btnShowUser)
        btnGoToLogin = v.findViewById(R.id.goToLogin)
        btnGoToRegister = v.findViewById(R.id.goToRegister)

        btnShowUser.setOnClickListener(){
            userEmail.text = user?.email.toString()
        }
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}