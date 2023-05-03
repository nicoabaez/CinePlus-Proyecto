package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ort.cineplus.adapters.MovieAdapter
import com.ort.cineplus.databinding.FragmentListaBinding
import com.ort.cineplus.entities.PopularMoviesRepository
import com.ort.cineplus.models.PopularMovies
import com.ort.cineplus.models.TheMovieDbService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.appcompat.widget.SearchView.OnQueryTextListener

class ListaFragment : Fragment(), OnQueryTextListener {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovieAdapter
    private var repository: PopularMoviesRepository = PopularMoviesRepository()
    private var movieList = repository.movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater,container,false)
        initRecyclerView()
        binding.searchMovie.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getPopularMovies()
    }

    private fun initRecyclerView(){
        binding.recyclerMovie.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        adapter = MovieAdapter(movieList){position ->
            val action = ListaFragmentDirections.actionListaFragmentToDetalleFragment(movieList[position])
            findNavController().navigate(action)
        }
        binding.recyclerMovie.adapter = adapter
    }

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val service = retrofit.create(TheMovieDbService::class.java)
            val call: Call<PopularMovies> = service.getPopularMovies("movie/popular?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&page=1")

            call.enqueue(object : Callback<PopularMovies> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                    if (response.isSuccessful) {
                        val respuestaPeliculas = response.body()?.results ?: emptyList()
                        binding.textView.text = repository.getCategoria()
                        movieList.clear()
                        movieList.addAll(respuestaPeliculas)
                        Log.d("PELICULAS AÑADIDAS:", "PELICULAS AÑADIDAS: $respuestaPeliculas")
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                    Toast.makeText(context, "HA OCURRIDO UN ERROR:", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun buscarPorNombre(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val service = retrofit.create(TheMovieDbService::class.java)
            val call: Call<PopularMovies> = service.getPopularMovies("search/movie?api_key=63057ce88755d35487b8da66201da7b3&language=en-US&query=${query}&page=1&include_adult=false")
            call.enqueue(object : Callback<PopularMovies> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                    if (response.isSuccessful) {
                        val respuestaPeliculas = response.body()?.results ?: emptyList()
                        movieList.clear()
                        movieList.addAll(respuestaPeliculas)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                    showError()
                }
            })
            hideKeyboard()

        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(context, "HA OCURRIDO UN ERROR:", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            buscarPorNombre(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}