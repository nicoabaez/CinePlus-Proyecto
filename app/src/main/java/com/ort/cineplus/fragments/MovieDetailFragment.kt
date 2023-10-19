package com.ort.cineplus.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ort.cineplus.adapters.MovieAdapter
import com.ort.cineplus.databinding.FragmentMovieDetailBinding
import com.ort.cineplus.entities.MovieX
import com.ort.cineplus.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var similarMoviesAdapter: MovieAdapter

    private lateinit var movie: MovieX

    private lateinit var btnCommentList : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        btnCommentList = binding.btnCommentList

        btnCommentList.setOnClickListener{
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToCommentList(MovieDetailFragmentArgs.fromBundle(requireArguments()).movie.id)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        viewModel.loadSimilarMovies(movie.id)
        viewModel.similarMovieList.observe(viewLifecycleOwner) { movies -> initRecyclerView(movies.toMutableList()) }
    }

    private fun initRecyclerView(movieList: MutableList<MovieX>){
        binding.titleCategoryDetail.text = "SIMILAR MOVIES"
        binding.recyclerMovie.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        similarMoviesAdapter = MovieAdapter(movieList) { //movie ->
            //val action = MovieListFragmentDirections.actionListaFragmentToDetalleFragment(movie)
            //findNavController().navigate(action)
        }
        binding.recyclerMovie.adapter = similarMoviesAdapter
    }

    override fun onStart() {
        super.onStart()
        //val m = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.txtTitle.text = movie.title
        binding.txtDesc.text = movie.overview
        Glide.with(binding.imgView.context).load("https://image.tmdb.org/t/p/w200${ movie.poster_path }").into(binding.imgView)
    }

}