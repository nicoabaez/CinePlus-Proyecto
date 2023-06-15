package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.lifecycleScope
import com.ort.cineplus.R
import com.ort.cineplus.databinding.FragmentRegisterBinding
import com.ort.cineplus.entities.User
import com.ort.cineplus.viewmodels.RegisterViewModel
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var passConfirm: EditText
    private lateinit var btnRegister: Button
    private lateinit var user: User
    private val action = RegisterFragmentDirections.registerToLogin()
    private lateinit var v : View;
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)

        userName = binding.txtUserName
        email = binding.txtEmail
        pass = binding.txtPassword
        passConfirm = binding.txtPassConfirm
        btnRegister = binding.btnRegister
        return binding.root

    }


    override fun onStart() {
        super.onStart()
        btnRegister.setOnClickListener() {
            lifecycleScope.launch {
                if (checkCredentials()) {
                    user = User(
                        userName.text.toString(),
                        email.text.toString(),
                        pass.text.toString(),
                        false
                    )
                    viewModel.authRegister(user)
                    findNavController().navigate(action)

                } else {Snackbar.make(binding.root, "Could not register", Snackbar.LENGTH_LONG).show() }
                }
            }
        }
    private suspend fun checkCredentials(): Boolean {
        var isValid = true
        // Validar campo Email
        if (!viewModel.emailIsNotUsed(email)) {
            isValid = false
        }
        // Validar campo contraseña
        if (!viewModel.checkPass(passConfirm, pass)) {
            isValid = false
        }


        return isValid
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel

        }
    }
