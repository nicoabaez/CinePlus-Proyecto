package com.ort.cineplus.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ort.cineplus.R
import com.ort.cineplus.activities.LoginActivity


class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var btnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_login, container, false)

        email = v.findViewById(R.id.txtEmailLogin)
        pass = v.findViewById(R.id.txtPassLogin)
        btnLogin = v.findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(){
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel

    }

}