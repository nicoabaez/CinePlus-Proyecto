package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.cineplus.R

class ConfigApp : Fragment() {

    companion object {
        fun newInstance() = ConfigApp()
    }

    private lateinit var viewModel: ConfigAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_config_app, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigAppViewModel::class.java)
        // TODO: Use the ViewModel
    }

}