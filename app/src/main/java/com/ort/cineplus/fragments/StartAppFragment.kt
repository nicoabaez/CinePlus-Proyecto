package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.cineplus.R
import com.ort.cineplus.viewmodels.StartAppViewModel

class StartAppFragment : Fragment() {

    companion object {
        fun newInstance() = StartAppFragment()
    }

    private lateinit var viewModel: StartAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_app, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartAppViewModel::class.java)
        // TODO: Use the ViewModel
    }

}