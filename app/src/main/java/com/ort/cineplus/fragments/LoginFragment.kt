package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.cineplus.R
import com.ort.cineplus.activities.MainActivity
import com.ort.cineplus.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToRegister: Button
    private val action = LoginFragmentDirections.loginGoToRegister()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_login, container, false)

        email = v.findViewById(R.id.txtEmailLogin)
        pass = v.findViewById(R.id.txtPassLogin)
        btnLogin = v.findViewById(R.id.btnLogin)
        btnGoToRegister = v.findViewById(R.id.btnGoToRegister)

        btnLogin.setOnClickListener{
            if(!(email.text.isEmpty() || pass.text.isEmpty())){
                if(viewModel.login(email.text.toString(), pass.text.toString())){
                    startActivity(Intent(activity, MainActivity::class.java))
                }else{
                    Log.d("ERROR EN LOGIN:", "EL LOGIN DIO FALSO")
                    Snackbar.make(v, "User or Password incorrect..", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(v, "User or Password is empty..", Snackbar.LENGTH_LONG).show()
            }
        }

        btnGoToRegister.setOnClickListener{
            findNavController().navigate(action)
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: Use the ViewModel

    }
}