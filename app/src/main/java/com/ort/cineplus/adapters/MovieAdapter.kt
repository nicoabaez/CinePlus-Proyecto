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
    var onClick : (Int) -> Unit
    ) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val binding = ItemMovieBinding.bind(v)

        fun bind(m:MovieX){
            //this.setTitle(m.title)
            //this.setDesc(m.overview)
            this.setImage(m.poster_path)
        }

        //private fun setTitle(title: String) { binding.txtTitle.text = title    }
        //private fun setDesc(desc: String)   { binding.txtDesc.text = desc       }
        private fun setImage(img: String)   { Glide.with(binding.imgView.context).load("https://image.tmdb.org/t/p/w200${ img }").into(binding.imgView) }

        fun getCard(): CardView { return binding.movieCard }

    }

    override fun getItemCount(): Int { return movieList.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item : MovieX = movieList[position]
        holder.bind(item)
        holder.getCard().setOnClickListener { onClick(position) }
    }
}