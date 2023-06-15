package com.ort.cineplus.fragments


import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ort.cineplus.adapters.MovieAdapter
import com.ort.cineplus.databinding.FragmentMovieListBinding
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import com.ort.cineplus.entities.MovieX
import com.ort.cineplus.viewmodels.MovieListFragmentViewModel

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieListFragmentViewModel
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieListFragmentViewModel::class.java]
        viewModel.popularMovieList.observe(viewLifecycleOwner) { movies -> initRecyclerView(movies.toMutableList()) }
        viewModel.upcomingMovieList.observe(viewLifecycleOwner) { movies -> initRecyclerView2(movies.toMutableList()) }
        setupSearchView()
    }

    private fun initRecyclerView(movieList: MutableList<MovieX>){
        binding.recyclerMovie.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        popularMoviesAdapter = MovieAdapter(movieList) { movie ->
            val action = MovieListFragmentDirections.actionListaFragmentToDetalleFragment(movie)
            findNavController().navigate(action)
        }
        binding.recyclerMovie.adapter = popularMoviesAdapter
    }
    private fun initRecyclerView2(movieList: MutableList<MovieX>){
        binding.recyclerMovie2.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        upcomingMoviesAdapter = MovieAdapter(movieList) { movie ->
            val action = MovieListFragmentDirections.actionListaFragmentToDetalleFragment(movie)
            findNavController().navigate(action)
        }
        binding.recyclerMovie2.adapter = upcomingMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*private fun initRecyclerView(movieList: MutableList<MovieX>) {
        if (!::adapter.isInitialized) {
            adapter = MovieAdapter(movieList) { movie ->
                val action = MovieListFragmentDirections.actionListaFragmentToDetalleFragment(movie)
                findNavController().navigate(action)
            }
            binding.recyclerMovie.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerMovie.adapter = adapter
        }else{
            adapter.updateMovieList(movieList)
        }
    }    */


    private fun hideKeyboard() {
        val imm: InputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun setupSearchView() {
        binding.searchMovie.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.searchMoviesByName(query)
                    hideKeyboard()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }
}