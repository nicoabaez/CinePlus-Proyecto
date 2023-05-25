package com.ort.cineplus.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ort.cineplus.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val m = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie

        binding.txtTitle.text = m.title
        binding.txtDesc.text = m.overview
        Glide.with(binding.imgView.context).load("https://image.tmdb.org/t/p/w200${ m.poster_path }").into(binding.imgView)
    }

}