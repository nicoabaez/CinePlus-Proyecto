package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

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
            user = User(0, userName.text.toString(), email.text.toString(), pass.text.toString() )
            viewModel.registerUser(user);
        }

        return v;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel

        }
    }
