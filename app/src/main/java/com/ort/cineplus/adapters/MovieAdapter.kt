package com.ort.cineplus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.cineplus.R
import com.ort.cineplus.databinding.ItemMovieBinding
import com.ort.cineplus.entities.MovieX

class MovieAdapter (
    private var movieList: MutableList<MovieX>,
    private var onClick : (MovieX) -> Unit
    ) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val binding = ItemMovieBinding.bind(v)

        fun bind(m:MovieX){
            this.setImage(m.poster_path) // Imagen la pelicula en el recylerView
        }
        private fun setImage(img: String)   { Glide.with(binding.imgView.context).load("https://image.tmdb.org/t/p/original/${ img }").dontAnimate().into(binding.imgView) }

        fun getCard(): CardView { return binding.movieCard }

    }

    override fun getItemCount(): Int { return movieList.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.getCard().setOnClickListener { onClick(movie) }
    }
    //fun getItem(position: Int): MovieX { return movieList[position] }
    /*@SuppressLint("NotifyDataSetChanged")
    fun updateMovieList(newList: MutableList<MovieX>) {
        movieList = newList.toMutableList()
        notifyDataSetChanged()
    }*/
}