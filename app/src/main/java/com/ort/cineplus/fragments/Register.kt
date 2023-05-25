package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import com.ort.cineplus.R
import com.ort.cineplus.entities.User


class Register : Fragment() {

    companion object {
        fun newInstance() = Register()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var passConfirm: EditText
    private lateinit var btnRegister: Button
    private lateinit var user: User
    private val action = RegisterDirections.registerToLogin()


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var v = inflater.inflate(R.layout.fragment_register, container, false)

        userName = v.findViewById(R.id.txtUserName)
        email = v.findViewById(R.id.txtEmail)
        pass = v.findViewById(R.id.txtPassword)
        passConfirm = v.findViewById(R.id.txtPassConfirm)
        btnRegister = v.findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener() {
            if (viewModel.checkPass(pass.text.toString(), passConfirm.text.toString())) {
                user = User(
                    userName.text.toString(),
                    email.text.toString(),
                    pass.text.toString(),
                    false
                )
                if (viewModel.authRegister(user)) {
                    Snackbar.make(v, "User registered succefully", Snackbar.LENGTH_LONG).show()
                    findNavController().navigate(action);
                } else {
                    Snackbar.make(v, "The email already exist...", Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(v, "Invalid passwords", Snackbar.LENGTH_LONG).show()
            }
        }
        return v;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel

        }
    }
